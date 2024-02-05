package com.newsapp.auth.filter;

import com.newsapp.auth.service.UserDetailsServiceImpl;
import com.newsapp.auth.exception.InvalidOrEmptyHeaderException;
import com.newsapp.auth.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException,InvalidOrEmptyHeaderException {
        log.info("---------> entered com.newsapp.auth.filter.JwtAuthFilter.doFilterInternal");

        // Check if the request path is a public endpoint
        if (isPublicEndpoint(request)) {
            // Allow the request to proceed without token validation
            filterChain.doFilter(request, response);
            return;
        }
        else{
            String authHeader = request.getHeader("Authorization");
            log.trace(authHeader);
            String token = null;
            String username = null;


            if(authHeader != null && authHeader.startsWith("Bearer ")){
                token = authHeader.substring(7);
                log.trace(token);
                username = jwtUtil.extractUsername(token);
                log.trace("user name extracted "+username);
            }
            else{
                throw new InvalidOrEmptyHeaderException(" Invalid Or Empty Header Exception");
            }

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
                if(jwtUtil.validateToken(token, userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

            }

            filterChain.doFilter(request, response);
        }
    }

    private boolean isPublicEndpoint(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.trace(requestURI);
        // Define a list of public endpoint patterns
        List<String> publicEndpointPatterns = Arrays.asList("/newsapp/v1/auth/log","/manage","/swagger","/docs");

        // Check if the request URI matches any of the public endpoint patterns
        for (String pattern : publicEndpointPatterns) {
            if (requestURI.startsWith(pattern)) {
                return true; // It's a public endpoint
            }
        }

        return false; // It's not a public endpoint
    }
}
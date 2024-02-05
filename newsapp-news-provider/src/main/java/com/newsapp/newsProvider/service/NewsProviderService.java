package com.newsapp.newsProvider.service;

import com.newsapp.newsProvider.model.NewsApiResponse;
import com.newsapp.newsProvider.model.NewsArticle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
public class NewsProviderService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${news-provider.third-party-endpoint}")
    private String URL;
    @Value("${news-provider.api-key}")
    private String xRapidApiKey ;
    @Value("${news-provider.host}")
    private String xRapidApiHost;

    public List<NewsArticle> searchNewsArticles(String topic) {
        try{
            HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.set("X-RapidAPI-Key",xRapidApiKey);
            headers.set("X-RapidAPI-Host",xRapidApiHost);

            ResponseEntity<NewsApiResponse> response = restTemplate.exchange(
                    URL + topic,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    NewsApiResponse.class
            );
            log.info(response.toString());
            return response.getBody().getNews();
        }
        catch (Exception e){
            log.info("something is wrong");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Given Api key or Host are not valid..... :(",
                    e
            ) ;
        }
    }
}

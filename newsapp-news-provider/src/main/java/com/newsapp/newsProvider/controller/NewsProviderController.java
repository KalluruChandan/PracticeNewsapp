package com.newsapp.newsProvider.controller;

import com.newsapp.newsProvider.model.NewsArticle;
import com.newsapp.newsProvider.service.NewsProviderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(
        path = "/newsapp/app/news-provider"
)
public class NewsProviderController {

    @Autowired
    private NewsProviderService newsProviderService;

    @GetMapping(
            path = "/serch/{topic}"
    )
    public ResponseEntity<List<NewsArticle>> searchNewsArticles(
            @PathVariable("topic")String topic
    ){

            return new ResponseEntity<>(
                    newsProviderService.searchNewsArticles(topic),
                    HttpStatus.OK
            );
    }
}

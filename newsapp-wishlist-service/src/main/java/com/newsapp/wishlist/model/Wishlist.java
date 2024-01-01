package com.newsapp.wishlist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "wishist")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wishlist {
    @Id
    private String id;
    private String userName;
    private List<NewsArticle> articles;
}
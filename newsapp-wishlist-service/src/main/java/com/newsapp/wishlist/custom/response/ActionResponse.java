package com.newsapp.wishlist.custom.response;

import com.newsapp.wishlist.model.Action;
import com.newsapp.wishlist.model.NewsArticle;
import com.newsapp.wishlist.model.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionResponse {
    private Action actionPerformed;
    private Result result;
    private String actionMessage;
    private HttpStatus status;
    private NewsArticle articleEffected;

    public ActionResponse(Action actionPerformed, Result result, String actionMessage, HttpStatus status) {
        this.actionPerformed = actionPerformed;
        this.result = result;
        this.actionMessage = actionMessage;
        this.status = status;
    }
}

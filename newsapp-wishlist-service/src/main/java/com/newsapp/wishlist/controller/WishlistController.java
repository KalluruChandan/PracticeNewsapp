package com.newsapp.wishlist.controller;

import com.newsapp.wishlist.custom.response.ActionResponse;
import com.newsapp.wishlist.model.Wishlist;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * All properties are found in .properties file
 *
*/

@RestController
@RequestMapping(
        path = "${wishlist.endpoint.common}"
)
public class WishlistController {

    @GetMapping(
            path = "${wishlist.endpoint.get-wishlist}"
    )
    @Operation(
            summary = "Get all the articles wishlisted by the user.",
            description = "This endpoint will help getting all the articles wishlisted by the user."
    )
    public ResponseEntity<Wishlist> getArticlesWishListed(){
        return null;
    }

    @PostMapping(
            path = "${wishlist.endpoint.add-to-wishlist}"
    )
    @Operation(
            summary = "add news article to wishlist.",
            description = "This endpoint will help adding news article to wishlist."
    )
    public ResponseEntity<ActionResponse> addArticleToWishlist(){
        return null;
    }

    @DeleteMapping(
            path = "${wishlist.endpoint.remove-from-wishlist}"
    )
    @Operation(
            summary = "unwishlist the news article",
            description = "This endpoint will help remove the news article from the wishlist."
    )
    public ResponseEntity<ActionResponse> removeArticleFromWishlist(){
        return null;
    }
}

package com.newsapp.wishlist.repository;

import com.newsapp.wishlist.model.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends MongoRepository<Wishlist,String> {
}

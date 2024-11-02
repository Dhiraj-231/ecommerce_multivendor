package com.dhiraj.ecommerce_multivendor.Service;

import com.dhiraj.ecommerce_multivendor.Modals.Product;
import com.dhiraj.ecommerce_multivendor.Modals.User;
import com.dhiraj.ecommerce_multivendor.Modals.Wishlist;

public interface WishListService {

    Wishlist createWishList(User user);

    Wishlist getWishListByUserId(User user);

    Wishlist addProductToWishList(User user, Product product);
}

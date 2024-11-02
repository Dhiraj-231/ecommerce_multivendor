package com.dhiraj.ecommerce_multivendor.Service.impl;

import org.springframework.stereotype.Service;

import com.dhiraj.ecommerce_multivendor.Modals.Product;
import com.dhiraj.ecommerce_multivendor.Modals.User;
import com.dhiraj.ecommerce_multivendor.Modals.Wishlist;
import com.dhiraj.ecommerce_multivendor.Repository.WishListRepository;
import com.dhiraj.ecommerce_multivendor.Service.WishListService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {

    private final WishListRepository wishListRepository;

    @Override
    public Wishlist createWishList(User user) {
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);

        return wishListRepository.save(wishlist);
    }

    @Override
    public Wishlist getWishListByUserId(User user) {
        Wishlist wishlist = wishListRepository.findByUserId(user.getId());
        if (wishlist != null) {
            return wishlist;
        }
        return createWishList(user);
    }

    @Override
    public Wishlist addProductToWishList(User user, Product product) {
        Wishlist wishlist = getWishListByUserId(user);
        if (wishlist.getProducts().contains(product)) {
            wishlist.getProducts().remove(product);
        } else {
            wishlist.getProducts().add(product);
        }
        return wishListRepository.save(wishlist);

    }

}

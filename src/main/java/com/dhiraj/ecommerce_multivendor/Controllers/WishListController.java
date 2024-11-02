package com.dhiraj.ecommerce_multivendor.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhiraj.ecommerce_multivendor.Modals.Product;
import com.dhiraj.ecommerce_multivendor.Modals.User;
import com.dhiraj.ecommerce_multivendor.Modals.Wishlist;
import com.dhiraj.ecommerce_multivendor.Service.ProductService;
import com.dhiraj.ecommerce_multivendor.Service.UserService;
import com.dhiraj.ecommerce_multivendor.Service.WishListService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishListController {
    private final WishListService wishListService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<Wishlist> getWishlistByUserId(@RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserByJwtToken(token);
        Wishlist wishlist = wishListService.getWishListByUserId(user);
        return ResponseEntity.ok(wishlist);
    }

    @PostMapping("/add-product/{productId}")
    public ResponseEntity<Wishlist> addProductToWishlist(@RequestHeader("Authorization") String token,
            @PathVariable Long productId) throws Exception {
        User user = userService.findUserByJwtToken(token);
        Product product = productService.findProductById(productId);
        Wishlist wishlist = wishListService.addProductToWishList(user, product);
        return ResponseEntity.ok(wishlist);
    }
}

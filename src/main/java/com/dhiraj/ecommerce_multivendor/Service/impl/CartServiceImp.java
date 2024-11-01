package com.dhiraj.ecommerce_multivendor.Service.impl;

import org.springframework.stereotype.Service;

import com.dhiraj.ecommerce_multivendor.Modals.Cart;
import com.dhiraj.ecommerce_multivendor.Modals.CartItem;
import com.dhiraj.ecommerce_multivendor.Modals.Product;
import com.dhiraj.ecommerce_multivendor.Modals.User;
import com.dhiraj.ecommerce_multivendor.Repository.CartItemRepository;
import com.dhiraj.ecommerce_multivendor.Repository.CartRepositiory;
import com.dhiraj.ecommerce_multivendor.Service.CartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImp implements CartService {

    private final CartItemRepository cartItemRepository;
    private final CartRepositiory cartRepositiory;

    @Override
    public CartItem addCartItem(User user, Product product, String size, int quantity) {
        Cart cart = finduserCart(user);

        CartItem isPresent = cartItemRepository.findByCartAndProductAndSize(cart, product, size);

        if (isPresent == null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUserId(user.getId());
            cartItem.setSize(size);

            int totalPrice = quantity * product.getSellingPrice();
            cartItem.setSellingPrice(totalPrice);
            cartItem.setMrpPrice(product.getMrpPrice() * quantity);
            cart.getCarItems().add(cartItem);
            cartItem.setCart(cart);

            return cartItemRepository.save(cartItem);
        }
        return isPresent;
    }

    @Override
    public Cart finduserCart(User user) {
        Cart cart = cartRepositiory.findByUserId(user.getId());

        int totalPrice = 0;
        int totalDiscountedPrice = 0;
        int totalItem = 0;

        for (CartItem cartItem : cart.getCarItems()) {
            totalPrice += cartItem.getMrpPrice();
            totalDiscountedPrice += cartItem.getSellingPrice();
            totalItem += cartItem.getQuantity();
        }

        cart.setTotalMrpPrice(totalPrice);
        cart.setTotalSellingPrice(totalDiscountedPrice);
        cart.setTotalItem(totalItem);
        cart.setDiscount(calculateDiscountPercentage(totalPrice, totalDiscountedPrice));

        return cartRepositiory.save(cart);
    }

    private int calculateDiscountPercentage(int mrPrice, int sellingPrice) {
        if (mrPrice <= 0 || sellingPrice <= 0) {
            return 0;
        }
        double discount = mrPrice - sellingPrice;
        if (discount > 0) {
            return (int) ((discount / mrPrice) * 100);
        }
        return 0;
    }

}

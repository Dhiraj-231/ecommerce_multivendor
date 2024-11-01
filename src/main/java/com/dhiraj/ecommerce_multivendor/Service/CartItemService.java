package com.dhiraj.ecommerce_multivendor.Service;

import com.dhiraj.ecommerce_multivendor.Modals.CartItem;

public interface CartItemService {

    CartItem updateCartItem(Long userId, Long cartItemId, CartItem cartItem) throws Exception;

    void removeCartItem(Long userId, Long cartItemId) throws Exception;

    CartItem findCartItem(Long id) throws Exception;
}

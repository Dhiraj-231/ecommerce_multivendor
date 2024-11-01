package com.dhiraj.ecommerce_multivendor.Service.impl;

import org.springframework.stereotype.Service;

import com.dhiraj.ecommerce_multivendor.Modals.CartItem;
import com.dhiraj.ecommerce_multivendor.Modals.User;
import com.dhiraj.ecommerce_multivendor.Repository.CartItemRepository;
import com.dhiraj.ecommerce_multivendor.Service.CartItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem updateCartItem(Long userId, Long cartItemId, CartItem cartItem) throws Exception {
        CartItem item = findCartItem(cartItemId);

        User cartItemUser = item.getCart().getUser();

        if (cartItemUser.getId().equals(userId)) {

            item.setQuantity(cartItem.getQuantity());
            item.setMrpPrice(item.getQuantity() * item.getProduct().getMrpPrice());
            item.setSellingPrice(item.getQuantity() * item.getProduct().getSellingPrice());
            return cartItemRepository.save(item);

        }
        throw new Exception("You are not authorized to update this cart item");
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws Exception {
        CartItem item = findCartItem(cartItemId);
        User cartItemUser = item.getCart().getUser();

        if (cartItemUser.getId().equals(userId)) {
            cartItemRepository.delete(item);
            return;
        }

        throw new Exception("You are not authorized to delete this cart item");
    }

    @Override
    public CartItem findCartItem(Long id) throws Exception {
        return cartItemRepository.findById(id).orElseThrow(() -> new Exception("Cart item not found" + id));
    }

}

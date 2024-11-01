package com.dhiraj.ecommerce_multivendor.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhiraj.ecommerce_multivendor.Modals.Cart;
import com.dhiraj.ecommerce_multivendor.Modals.CartItem;
import com.dhiraj.ecommerce_multivendor.Modals.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);
}

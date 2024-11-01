package com.dhiraj.ecommerce_multivendor.Service;

import com.dhiraj.ecommerce_multivendor.Modals.Cart;
import com.dhiraj.ecommerce_multivendor.Modals.CartItem;
import com.dhiraj.ecommerce_multivendor.Modals.Product;
import com.dhiraj.ecommerce_multivendor.Modals.User;

public interface CartService {

    public CartItem addCartItem(User user, Product product, String size, int quantity);

    public Cart finduserCart(User user);

}

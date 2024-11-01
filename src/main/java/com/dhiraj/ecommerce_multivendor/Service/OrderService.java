package com.dhiraj.ecommerce_multivendor.Service;

import java.util.List;
import java.util.Set;

import com.dhiraj.ecommerce_multivendor.Domin.OrderStatus;
import com.dhiraj.ecommerce_multivendor.Modals.Address;
import com.dhiraj.ecommerce_multivendor.Modals.Cart;
import com.dhiraj.ecommerce_multivendor.Modals.Order;
import com.dhiraj.ecommerce_multivendor.Modals.OrderItem;
import com.dhiraj.ecommerce_multivendor.Modals.User;

public interface OrderService {

    Set<Order> createOrder(User user, Address shippingAddress, Cart cart);

    Order findOrderById(Long id) throws Exception;

    List<Order> usersOrderHistory(Long userId);

    List<Order> sellersOrder(Long sellerId);

    Order updateOrderStatus(Long orderId, OrderStatus orderStatus) throws Exception;

    Order cancelOrder(Long orderId, User user) throws Exception;

    OrderItem findOrderItemById(Long id) throws Exception;
}

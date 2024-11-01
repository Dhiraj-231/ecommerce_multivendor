package com.dhiraj.ecommerce_multivendor.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhiraj.ecommerce_multivendor.Modals.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);

    List<Order> findBySellerId(Long sellerId);
}

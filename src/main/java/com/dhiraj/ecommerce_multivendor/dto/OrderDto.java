package com.dhiraj.ecommerce_multivendor.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.dhiraj.ecommerce_multivendor.Domin.OrderStatus;
import com.dhiraj.ecommerce_multivendor.Domin.PaymentStatus;
import com.dhiraj.ecommerce_multivendor.Modals.Address;
import com.dhiraj.ecommerce_multivendor.Modals.PaymentDetails;

import lombok.Data;

@Data
public class OrderDto {
    private Long id;

    private String orderId;

    private UserDto user;

    private Long sellerId;

    private List<OrderItemDto> orderItems = new ArrayList<>();

    private Address shippingAddress;

    private PaymentDetails paymentDetails=new PaymentDetails();

    private double totalMrpPrice;

    private Integer totalSellingPrice;

    private Integer discount;

    private OrderStatus orderStatus;

    private int totalItem;

    private PaymentStatus paymentStatus=PaymentStatus.PENDING;

    private LocalDateTime orderDate = LocalDateTime.now();
    private LocalDateTime deliverDate = orderDate.plusDays(7);
}

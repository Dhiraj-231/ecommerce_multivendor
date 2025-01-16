package com.dhiraj.ecommerce_multivendor.dto;

import java.util.List;

import lombok.Data;

@Data
public class  OrderHistory {
    private Long id;
    private UserDto user;
    private List<OrderDto> currentOrders;
    private int totalOrders;
    private int cancelledOrders;
    private int completedOrders;
    private int pendingOrders;
}

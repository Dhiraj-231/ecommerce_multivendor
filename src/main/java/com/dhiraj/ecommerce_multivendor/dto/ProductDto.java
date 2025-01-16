package com.dhiraj.ecommerce_multivendor.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;

    private String title;

    private String description;

    private int mrpPrice;

    private int sellingPrice;

    private int discountPercent;

    private int quantity;

    private String color;

    private List<String> images = new ArrayList<>();

    private int numRatings;
}

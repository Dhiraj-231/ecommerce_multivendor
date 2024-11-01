package com.dhiraj.ecommerce_multivendor.Request;

import java.util.List;

import lombok.Data;

@Data
public class CreateProductRequest {

    private String title;
    private String description;
    private int mrPrice;
    private int sellingPrice;
    private String color;
    private List<String> imageUrls;
    private String category;
    private String category2;
    private String category3;

    private String size;
}

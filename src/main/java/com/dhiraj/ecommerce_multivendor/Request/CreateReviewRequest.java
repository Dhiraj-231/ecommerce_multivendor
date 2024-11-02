package com.dhiraj.ecommerce_multivendor.Request;

import java.util.List;

import lombok.Data;

@Data
public class CreateReviewRequest {
    private String reviewText;
    private double reviewRating;
    private List<String> productImages;
}
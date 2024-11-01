package com.dhiraj.ecommerce_multivendor.Response;

import com.dhiraj.ecommerce_multivendor.Domin.USER_ROLE;

import lombok.Data;

@Data
public class AuthResponse {

    private String token;
    private String message;
    private USER_ROLE role;
}

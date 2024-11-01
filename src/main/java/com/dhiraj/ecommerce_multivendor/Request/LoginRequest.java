package com.dhiraj.ecommerce_multivendor.Request;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String otp;
}

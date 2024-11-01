package com.dhiraj.ecommerce_multivendor.Request;

import com.dhiraj.ecommerce_multivendor.Domin.USER_ROLE;

import lombok.Data;

@Data
public class LoginOtpRequest {
    private String email;
    private String otp;
    private USER_ROLE role;
}

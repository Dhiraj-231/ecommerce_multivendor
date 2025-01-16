package com.dhiraj.ecommerce_multivendor.Request;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String password;
    private String token;
}

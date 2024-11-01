package com.dhiraj.ecommerce_multivendor.Response;

import lombok.Data;

@Data
public class SignupRequest {

    private String email;
    private String FullName;
    private String otp;

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return FullName;
    }

    public String getOtp() {
        return otp;
    }
}

package com.dhiraj.ecommerce_multivendor.Service;

import com.dhiraj.ecommerce_multivendor.Domin.USER_ROLE;
import com.dhiraj.ecommerce_multivendor.Request.LoginRequest;
import com.dhiraj.ecommerce_multivendor.Response.AuthResponse;
import com.dhiraj.ecommerce_multivendor.Response.SignupRequest;

public interface AuthService {

    String registerUser(SignupRequest req) throws Exception;

    void sentLoginOTP(String email, USER_ROLE role) throws Exception;

    AuthResponse loginUser(LoginRequest req) throws Exception;
}

package com.dhiraj.ecommerce_multivendor.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhiraj.ecommerce_multivendor.Domin.USER_ROLE;
import com.dhiraj.ecommerce_multivendor.Request.LoginOtpRequest;
import com.dhiraj.ecommerce_multivendor.Request.LoginRequest;
import com.dhiraj.ecommerce_multivendor.Response.ApiResponse;
import com.dhiraj.ecommerce_multivendor.Response.AuthResponse;
import com.dhiraj.ecommerce_multivendor.Response.SignupRequest;
import com.dhiraj.ecommerce_multivendor.Service.impl.AuthServiceImp;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImp authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody SignupRequest req) throws Exception {

        String jwt = authService.registerUser(req);
        AuthResponse response = new AuthResponse();
        response.setMessage("User Registered Successfully");
        response.setToken(jwt);
        response.setRole(USER_ROLE.ROLE_CUSTOMER);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/sent/login-sign-up-otp")
    public ResponseEntity<ApiResponse> sentOtpHandler(@RequestBody LoginOtpRequest req) throws Exception {
        authService.sentLoginOTP(req.getEmail(), req.getRole());

        ApiResponse response = new ApiResponse();
        response.setMessage("OTP sent to your email");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signin")

    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest req) throws Exception {
        AuthResponse authResponse = authService.loginUser(req);

        return ResponseEntity.ok(authResponse);
    }
}
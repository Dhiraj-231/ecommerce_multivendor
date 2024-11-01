package com.dhiraj.ecommerce_multivendor.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhiraj.ecommerce_multivendor.Response.ApiResponse;

@RestController
public class HomeController {

    @GetMapping("/")
    public ApiResponse HomeControllerHandler() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Welcome to the ecommerce MultiVendor System ");
        return apiResponse;
    }
}

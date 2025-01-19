package com.dhiraj.ecommerce_multivendor.Ai.Service.Imp;

import com.dhiraj.ecommerce_multivendor.Ai.Service.AiProductService;
import com.dhiraj.ecommerce_multivendor.Exceptions.ProductException;
import com.dhiraj.ecommerce_multivendor.Response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiProductServiceImp implements AiProductService {

    @Override
    public String simpleChat(String prompt) {
        return "";
    }
}

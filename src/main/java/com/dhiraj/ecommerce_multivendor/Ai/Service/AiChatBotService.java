package com.dhiraj.ecommerce_multivendor.Ai.Service;

import com.dhiraj.ecommerce_multivendor.Exceptions.ProductException;
import com.dhiraj.ecommerce_multivendor.Response.ApiResponse;

public interface AiChatBotService {
    ApiResponse aiChatBot(String prompt, Long productId, Long userId) throws ProductException;
}

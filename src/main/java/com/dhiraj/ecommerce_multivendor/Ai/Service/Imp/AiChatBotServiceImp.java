package com.dhiraj.ecommerce_multivendor.Ai.Service.Imp;

import com.dhiraj.ecommerce_multivendor.Ai.Service.AiChatBotService;
import com.dhiraj.ecommerce_multivendor.Exceptions.ProductException;
import com.dhiraj.ecommerce_multivendor.Repository.CartRepositiory;
import com.dhiraj.ecommerce_multivendor.Repository.OrderRepository;
import com.dhiraj.ecommerce_multivendor.Repository.ProductRepository;
import com.dhiraj.ecommerce_multivendor.Repository.UserRepo;
import com.dhiraj.ecommerce_multivendor.Response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiChatBotServiceImp implements AiChatBotService {
    String GEMINI_API_KEY = "AIzaSyDp-jeRRqqbr08scpIn1p9rLEL_Nqv5Zuo";

    private final CartRepositiory cartRepository;

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;
    private final UserRepo userRepository;
    @Override
    public ApiResponse aiChatBot(String prompt, Long productId, Long userId) throws ProductException {
        return null;
    }
}

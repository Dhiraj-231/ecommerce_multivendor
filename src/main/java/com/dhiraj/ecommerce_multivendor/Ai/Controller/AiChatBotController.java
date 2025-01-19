package com.dhiraj.ecommerce_multivendor.Ai.Controller;

import com.dhiraj.ecommerce_multivendor.Ai.Service.AiChatBotService;
import com.dhiraj.ecommerce_multivendor.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/ai/chat")
@RestController
@RequiredArgsConstructor
public class AiChatBotController {
    private final AiChatBotService aiChatBotService;
    private final UserService userService;
}

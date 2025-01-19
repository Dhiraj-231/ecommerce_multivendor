package com.dhiraj.ecommerce_multivendor.Ai.Controller;

import com.dhiraj.ecommerce_multivendor.Ai.Service.AiChatBotService;
import com.dhiraj.ecommerce_multivendor.Modals.User;
import com.dhiraj.ecommerce_multivendor.Request.Prompt;
import com.dhiraj.ecommerce_multivendor.Response.ApiResponse;
import com.dhiraj.ecommerce_multivendor.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ai/chat")
@RestController
@RequiredArgsConstructor
public class AiChatBotController {
    private final AiChatBotService aiChatBotService;
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<ApiResponse> generate(
            @RequestBody Prompt prompt,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long productId,
            @RequestHeader(required = false,name = "Authorization")String jwt) throws Exception {

        String message = prompt.getPrompt();
        if (productId != null) {
            message = "the product id is " + productId +", " + message ;
        }

        User user=new User();
        if(jwt!=null)
            user=userService.findUserByJwtToken(jwt);

        ApiResponse apiResponse = aiChatBotService.aiChatBot(message,productId,user.getId());

        return ResponseEntity.ok(apiResponse);

    }
}

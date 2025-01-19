package com.dhiraj.ecommerce_multivendor.Ai.Controller;

import com.dhiraj.ecommerce_multivendor.Ai.Service.AiProductService;
import com.dhiraj.ecommerce_multivendor.Response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiProductController {
    private final AiProductService productService;

    @PostMapping("/chat/demo")
    public ApiResponse generate(@RequestParam(
            value = "message",
            defaultValue = "Tell me a joke") String message) throws Exception {

        String ans = productService.simpleChat(message);

        JSONObject messageJson = new JSONObject(ans);

        // Extract the text from the JSON
        JSONArray candidates = messageJson.getJSONArray("candidates");
        JSONObject content = candidates.getJSONObject(0).getJSONObject("content");
        JSONArray parts = content.getJSONArray("parts");
        String text = parts.getJSONObject(0).getString("text");

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(text);

        return apiResponse;

    }
}

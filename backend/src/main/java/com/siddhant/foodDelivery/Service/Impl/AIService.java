package com.siddhant.foodDelivery.Service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class AIService {
    @Value("${gemini.api.url}")
    String ApiUrl;
    @Value("${gemini.api.key}")
    String ApiKey;

    private final WebClient webClient;

    public AIService(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }


    public String getReceipe(String question) {
        Map<String,Object> requestBody=Map.of(
                "contents",new Object[]{
                        Map.of("parts",new Object[]{
                                Map.of("text",question)
                        })
                }
        );
        String response=webClient.post()
                .uri(ApiUrl+ApiKey)
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }
}

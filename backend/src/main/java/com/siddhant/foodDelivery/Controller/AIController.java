package com.siddhant.foodDelivery.Controller;


import com.siddhant.foodDelivery.Service.Impl.AIService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.sax.SAXResult;
import java.util.Map;

@RequestMapping("/api/ai")
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@AllArgsConstructor
public class AIController {
    private final AIService aiService;

    @PostMapping("/ask")
    String Getresponse(@RequestBody String question){
        String actual="Suggest only 1 distinct recipe only without any comment , with given ingredients and in response make sure ingredients are listed in numbered list and steps also listed in numbered list , here is the list of Ingredients -> "+question;
        return aiService.getReceipe(actual);
    }
}

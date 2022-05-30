package com.twowasik_project.controller;

import com.twowasik_project.dto.AuthenticationRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {
    @PostMapping("/test")
    public ResponseEntity test(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        String email = authenticationRequestDto.getEmail();
        System.out.println(email);
        Map<Object, Object> response = new HashMap<>();
        response.put(email, email);
        return ResponseEntity.ok(response);
    }
}

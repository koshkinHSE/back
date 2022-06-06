package com.twowasik_project.model.controller;

import com.twowasik_project.jwt.JWTProvider;
import com.twowasik_project.model.User;
import com.twowasik_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {

    @Autowired
    private JWTProvider jwtProvider;

//    @GetMapping("/test")
//    public ResponseEntity test(HttpServletRequest request) {
//        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
//            return ResponseEntity.notFound().build();
//        }
//
//    }
}
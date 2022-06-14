package com.twowasik.messenger.rest;

import com.twowasik.messenger.repository.UserRepository;
import com.twowasik.messenger.exceptions.InvalidTokenExceptions;
import com.twowasik.messenger.jwt.JWTProvider;
//import com.twowasik.messenger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
@RestController
@RequestMapping(value = "/adding/")
public class AddingController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTProvider jwtProvider;

    @PostMapping("userSearch/{username}")
    public ResponseEntity<?> findUsers(HttpServletRequest request, @PathVariable String username) {

        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
            throw new InvalidTokenExceptions();
        }

        return ResponseEntity.ok(userRepository.findUsers(username));
    }
}

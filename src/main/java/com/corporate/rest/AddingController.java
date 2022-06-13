package com.corporate.rest;

import com.corporate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
@RestController
@RequestMapping(value = "/adding/")
public class AddingController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("userSearch/{username}")
    public ResponseEntity<?> findUsers(@PathVariable String username) {
        return ResponseEntity.ok(userRepository.findUsers(username));
    }
}

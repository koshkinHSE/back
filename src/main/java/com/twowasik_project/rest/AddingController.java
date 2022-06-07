package com.twowasik_project.rest;


import com.twowasik_project.model.User;
import com.twowasik_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

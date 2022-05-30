package com.twowasik_project.rest;

import com.twowasik_project.dto.AuthenticationRequestDto;
import com.twowasik_project.dto.RegistrationRequestDto;
import com.twowasik_project.model.Role;
import com.twowasik_project.model.User;
import com.twowasik_project.repository.RoleRepository;
import com.twowasik_project.repository.UserRepository;
import com.twowasik_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/auth/")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        String email = authenticationRequestDto.getEmail();
        String password = authenticationRequestDto.getPassword();
        User user = userRepository.findByEmail(email);

        if (user == null || !password.equals(user.getPassword())) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userService.makeJWToken(user));
    }

    @PostMapping("register")
    public ResponseEntity register(@RequestBody RegistrationRequestDto registrationRequestDto) {
        String email = registrationRequestDto.getEmail();
        String password = registrationRequestDto.getPassword();
        String name = registrationRequestDto.getName();

        User user = userRepository.findByEmail(email);

        if (user != null) {
            return ResponseEntity.notFound().build();
        }

        List<Role> defaultRole = new ArrayList<>();
        defaultRole.add(roleRepository.findByName("USER"));
        user = userService.saveUser(new User(email, password, name, defaultRole));

        return ResponseEntity.ok(userService.makeJWToken(user));
    }
}

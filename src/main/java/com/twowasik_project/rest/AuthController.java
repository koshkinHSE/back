package com.twowasik_project.rest;

import com.twowasik_project.dto.AuthenticationRequestDto;
import com.twowasik_project.dto.JwtDto;
import com.twowasik_project.dto.JwtRefreshDto;
import com.twowasik_project.dto.RegistrationRequestDto;

import com.twowasik_project.jwt.JWTProvider;
import com.twowasik_project.model.User;
import com.twowasik_project.repository.UserRepository;
import com.twowasik_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth/")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private JwtDto jwtDto;

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        String email = authenticationRequestDto.getEmail();
        String password = authenticationRequestDto.getPassword();
        User user = userRepository.findByEmail(email);

        if (user == null || !password.equals(user.getPassword())) {
            return ResponseEntity.notFound().build();
        }

        jwtDto.setAccessToken(jwtProvider.generateAccessToken(user));
        jwtDto.setRefreshToken(jwtProvider.generateRefreshToken(user));
        return ResponseEntity.ok(jwtDto);
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

        user = userService.saveUser(new User(email, password, name));

        jwtDto.setAccessToken(jwtProvider.generateAccessToken(user));
        jwtDto.setRefreshToken(jwtProvider.generateRefreshToken(user));
        return ResponseEntity.ok(jwtDto);
    }

    @PostMapping("token")
    public ResponseEntity register(@RequestBody JwtRefreshDto jwtRefreshDto) {
        if (!jwtProvider.validateRefreshToken(jwtRefreshDto.getRefreshToken())) {
            return ResponseEntity.notFound().build();
        }

        User user = userRepository.findByUsername(jwtProvider.getRefreshClaims(jwtRefreshDto.getRefreshToken()).getSubject());
        jwtDto.setAccessToken(jwtProvider.generateAccessToken(user));
        jwtDto.setRefreshToken(jwtProvider.generateRefreshToken(user));
        return ResponseEntity.ok(jwtDto);
    }
}

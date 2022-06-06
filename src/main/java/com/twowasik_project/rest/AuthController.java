package com.twowasik_project.rest;

import com.twowasik_project.dto.AuthenticationRequestDto;
import com.twowasik_project.dto.JwtDto;
import com.twowasik_project.dto.JwtRefreshDto;
import com.twowasik_project.dto.RegistrationRequestDto;

import com.twowasik_project.jwt.JWTProvider;
import com.twowasik_project.model.User;
import com.twowasik_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
@RestController
@RequestMapping(value = "/auth/")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private JwtDto jwtDto;

    @Autowired User user;

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        String email = authenticationRequestDto.getEmail();
        String password = authenticationRequestDto.getPassword();
        user = userService.findByEmail(email);

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

        if (userService.findByEmail(email) != null) {
            return ResponseEntity.ok(false);
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

        user = userService.findByUsername(jwtProvider.getRefreshClaims(jwtRefreshDto.getRefreshToken()).getSubject());
        jwtDto.setAccessToken(jwtProvider.generateAccessToken(user));
        jwtDto.setRefreshToken(jwtProvider.generateRefreshToken(user));
        return ResponseEntity.ok(jwtDto);
    }
}

package com.twowasik.messenger.rest;

import com.twowasik.messenger.dto.*;
import com.twowasik.messenger.exceptions.InvalidTokenExceptions;
import com.twowasik.messenger.jwt.JWTProvider;
import com.twowasik.messenger.model.User;
import com.twowasik.messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    User user;

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
        String avatar = registrationRequestDto.getAvatar();

        if (userService.findByEmail(email) != null) {
            return ResponseEntity.notFound().build();
        }

        user = userService.saveUser(new User(email, password, name, avatar));

        jwtDto.setAccessToken(jwtProvider.generateAccessToken(user));
        jwtDto.setRefreshToken(jwtProvider.generateRefreshToken(user));
        return ResponseEntity.ok(jwtDto);
    }

    @PostMapping("token")
    public ResponseEntity token(@RequestBody JwtRefreshDto jwtRefreshDto) {
        if (!jwtProvider.validateRefreshToken(jwtRefreshDto.getRefreshToken())) {
            return ResponseEntity.notFound().build();
        }

        user = userService.findByUsername(jwtProvider.getRefreshClaims(jwtRefreshDto.getRefreshToken()).getSubject());
        jwtDto.setAccessToken(jwtProvider.generateAccessToken(user));
        jwtDto.setRefreshToken(jwtProvider.generateRefreshToken(user));
        return ResponseEntity.ok(jwtDto);
    }
}
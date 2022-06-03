package com.twowasik_project.rest;

import com.twowasik_project.dto.AuthenticationRequestDto;
import com.twowasik_project.dto.ChangeUserDataDto;
import com.twowasik_project.dto.JwtDto;
import com.twowasik_project.exceptions.InvalidTokenExceptions;
import com.twowasik_project.jwt.JWTProvider;
import com.twowasik_project.model.User;
import com.twowasik_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
@RestController
@RequestMapping(value = "/change/")
public class ChangeController {

    @Autowired
    JWTProvider jwtProvider;

    @Autowired
    JwtDto jwtDto;

    @Autowired
    UserRepository userRepository;

    @PatchMapping("/userData")
    public ResponseEntity changeUserData(HttpServletRequest request, @RequestBody ChangeUserDataDto changeUserDataDto) {

        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
            throw new InvalidTokenExceptions();
        }

        User user = userRepository.findByUsername(jwtProvider.getAccessClaims(request.getHeader("Authorization")).getSubject());

        String newPassword = changeUserDataDto.getNewPassword();
        String oldPassword = changeUserDataDto.getOldPassword();
        String newAva = changeUserDataDto.getNewAva();
        String newUsername = changeUserDataDto.getNewUsername();

        if (!newAva.equals("false")) { userRepository.updateAvatar(newAva, user.getId()); }

        if (!newUsername.equals("false")) {
            userRepository.updateUsername(newUsername, user.getId());
            jwtDto.setAccessToken(jwtProvider.generateAccessToken(user));
            jwtDto.setRefreshToken(jwtProvider.generateRefreshToken(user));
        }

        if (!oldPassword.equals("false")) {
            if (!oldPassword.equals(user.getPassword())) { return ResponseEntity.ok(false);  }
            userRepository.updatePassword(newPassword, user.getId());
        }

        return ResponseEntity.ok(jwtDto);
    }
}
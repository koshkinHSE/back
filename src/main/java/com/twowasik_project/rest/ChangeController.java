package com.twowasik_project.rest;

import com.twowasik_project.dto.AuthenticationRequestDto;
import com.twowasik_project.dto.ChangeUserDataDto;
import com.twowasik_project.dto.JwtDto;
import com.twowasik_project.jwt.JWTProvider;
import com.twowasik_project.model.User;
import com.twowasik_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
            return ResponseEntity.badRequest().body("Unauthorized");
        }

        User user = userRepository.findByUsername(jwtProvider.getAccessClaims(request.getHeader("Authorization")).getSubject());

        String newPassword = changeUserDataDto.getNewPassword();
        String oldPassword = changeUserDataDto.getOldPassword();
        String newAva = changeUserDataDto.getNewAva();
        String newUsername = changeUserDataDto.getNewUsername();

        if (!newAva.equals("false")) { userRepository.updateAvatar(newAva, user.getId()); }

        jwtDto.setAccessToken("false");
        jwtDto.setRefreshToken("false");
        if (!newUsername.equals("false")) {
            userRepository.updateUsername(newUsername, user.getId());
            jwtDto.setAccessToken(jwtProvider.generateAccessToken(user));
            jwtDto.setRefreshToken(jwtProvider.generateRefreshToken(user));
        }

        if (!oldPassword.equals("false")) {
            if (!oldPassword.equals(user.getPassword())) { return ResponseEntity.notFound().build();  }
            userRepository.updatePassword(newPassword, user.getId());
            changeUserDataDto.setNewPassword("true");
        }

        return ResponseEntity.ok(jwtDto);
    }
}
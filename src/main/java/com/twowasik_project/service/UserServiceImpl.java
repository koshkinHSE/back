package com.twowasik_project.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.twowasik_project.model.Role;
import com.twowasik_project.model.User;
import com.twowasik_project.repository.RoleRepository;
import com.twowasik_project.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service @RequiredArgsConstructor @Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private User user;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Map<Object, Object> makeJWToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256("2wasik".getBytes());
        String accessToken =  JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withClaim("roles", getRolesNames(user.getRoles()))
                .sign(algorithm);

        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .sign(algorithm);

        Map<Object, Object> response = new HashMap<>();
        response.put("access-token", accessToken);
        response.put("refresh-token", refreshToken);

        return response;
    }

    private List<String> getRolesNames(List<Role> userRoles) {
        List<String> result = new ArrayList<>();
        userRoles.forEach(role -> { result.add(role.getName()); });
        return result;
    }
}

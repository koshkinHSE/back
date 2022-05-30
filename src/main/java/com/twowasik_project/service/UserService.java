package com.twowasik_project.service;

import com.twowasik_project.model.User;

import java.util.Map;

public interface UserService {
    public Map<Object, Object> makeJWToken(User user);

    public User saveUser(User user, String email, String password, String name);
}
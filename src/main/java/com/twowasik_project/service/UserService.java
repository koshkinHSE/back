package com.twowasik_project.service;

import com.twowasik_project.model.User;

public interface UserService {
    User saveUser(User user);

    User findByEmail(String email);

    User findByUsername(String username);

    String getUsersId(String participants, String admin);

    void addTeam(String teamId, String usersId);
}
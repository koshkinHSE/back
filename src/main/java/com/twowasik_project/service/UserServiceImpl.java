package com.twowasik_project.service;

import com.twowasik_project.model.User;
import com.twowasik_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor @Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public String getUsersId(String participants, String admin) {
        StringBuilder usersId = new StringBuilder(admin + " ");
        if (!participants.equals("")) {
            User user;
            for (String email : participants.split(" ")) {
                user = findByEmail(email);
                if (user == null) {
                    return "";
                }
                usersId.append(user.getId() + " ");
            }
        }
        return usersId.toString();
    }

    @Override
    public void addTeam(String teamId, String usersId) {
        int id;
        String teams;
        for (String userId: usersId.split(" ")) {
            id = Integer.parseInt(userId);
            teams = userRepository.findById(id).getTeams();
            if (teams == null) { teams = ""; }
            userRepository.updateTeams(teams + teamId + " ", id);
        }
    }

    @Override
    public List<Integer> getTeams(String name) {
        List<Integer> teamsId = new ArrayList<>();
        String teams = userRepository.findByUsername(name).getTeams();
        if (teams == null) { return teamsId; }
        for (String id: teams.split(" ")) {
            teamsId.add(Integer.parseInt(id));
        }
        return teamsId;
    }
}

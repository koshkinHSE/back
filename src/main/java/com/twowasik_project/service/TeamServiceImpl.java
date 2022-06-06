package com.twowasik_project.service;

import com.twowasik_project.model.Team;
import com.twowasik_project.repository.TeamRepository;
import com.twowasik_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamServiceImpl implements TeamService{
    private final UserRepository userRepository;
    private final TeamRepository TeamRepository;

    @Override
    public Team saveTeam(Team team) {
        return TeamRepository.save(team);
    }

}
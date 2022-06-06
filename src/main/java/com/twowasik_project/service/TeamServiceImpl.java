package com.twowasik_project.service;

import com.twowasik_project.model.Team;
import com.twowasik_project.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor @Slf4j
public class TeamServiceImpl implements TeamService{

    private final TeamRepository teamRepository;

    @Override
    public int saveTeam(Team team) {
        return teamRepository.save(team).getId();
    }
}
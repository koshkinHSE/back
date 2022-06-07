package com.twowasik_project.service;

import com.twowasik_project.model.Team;
import com.twowasik_project.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor @Slf4j
public class TeamServiceImpl implements TeamService{

    private final TeamRepository teamRepository;

    @Override
    public int saveTeam(Team team) {
        return teamRepository.save(team).getId();
    }

    @Override
    public  String getNameById(int id) {
        return teamRepository.findById(id).getName();
    }

    @Override
    public List<String> showTeams(List<Integer> teamsId) {
        List<String> names = new ArrayList<>();
        if (teamsId.size() == 0) { return names; }
        for (int id: teamsId) {
            names.add(getNameById(id));
        }
        return names;
    }

}
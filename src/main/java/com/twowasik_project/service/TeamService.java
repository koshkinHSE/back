package com.twowasik_project.service;

import com.twowasik_project.dto.GetTeamDto;
import com.twowasik_project.model.Team;

import java.util.List;

public interface TeamService {

    int saveTeam(Team newTeam);

    String getNameById(int id);

    List<String> showTeams(List<Integer> id);

    void addPerson(int teamId, String participantsId);

    GetTeamDto getTeam(int teamId);
}
package com.twowasik.messenger.service;

import com.twowasik.messenger.dto.GetTeamDto;
import com.twowasik.messenger.model.Team;

import java.util.List;

public interface TeamService {

    int saveTeam(Team newTeam);

    String getNameById(int id);

    List<String> showTeams(List<Integer> id);

    void addPerson(int teamId, String participantsId);

    GetTeamDto getTeam(int teamId);
}
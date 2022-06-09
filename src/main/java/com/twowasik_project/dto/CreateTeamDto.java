package com.twowasik_project.dto;

import com.twowasik_project.model.User;
import lombok.Data;

import java.util.List;

@Data
public class CreateTeamDto {
    private String name;
    private String team_participants;
    private String avatar;
}
package com.twowasik_project.dto;

import lombok.Data;

@Data
public class CreateChannelDto {
    private String name;
    private String description;
    private int teamId;
}
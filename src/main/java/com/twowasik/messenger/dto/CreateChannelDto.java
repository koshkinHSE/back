package com.twowasik.messenger.dto;

import lombok.Data;

@Data
public class CreateChannelDto {
    private int team_id;
    private String name;
}
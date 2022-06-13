package com.twowasik.messenger.dto;

import lombok.Data;

@Data
public class GetTeamDto {
    private String name;
    private String avatar;

    public GetTeamDto(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }
}
package com.twowasik.messenger.dto;

import lombok.Data;

@Data
public class JwtRefreshDto {
    private String refreshToken;
}
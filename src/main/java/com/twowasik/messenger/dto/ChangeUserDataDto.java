package com.twowasik.messenger.dto;

import lombok.Data;

@Data
public class ChangeUserDataDto {
    private String newPassword;
    private String oldPassword;
    private String newAva;
    private String newUsername;
}
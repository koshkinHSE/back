package com.twowasik_project.dto;

import lombok.Data;

@Data
public class RegistrationRequestDto {
    private String email;
    private String password;
    private String name;
}

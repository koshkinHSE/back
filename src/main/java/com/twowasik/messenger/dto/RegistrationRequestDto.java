package com.twowasik.messenger.dto;

import lombok.Data;

@Data
public class RegistrationRequestDto {
    private String email;
    private String password;
    private String name;
    private String avatar;
}

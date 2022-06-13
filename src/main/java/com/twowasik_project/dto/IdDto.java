package com.twowasik_project.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data @Component
public class IdDto {
    private int id;

    public IdDto(int id) {
        this.id = id;
    }
}
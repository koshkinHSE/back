package com.twowasik_project.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data @Component
public class ShowTeamDto {
    private List<Integer> id;
    private List<String> name;
}
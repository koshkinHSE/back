package com.twowasik_project.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class ShowChannel {
    private List<Integer> id;
    private List<String> name;
    private List<String> description;
}
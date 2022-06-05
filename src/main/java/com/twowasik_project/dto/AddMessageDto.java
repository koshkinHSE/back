package com.twowasik_project.dto;

import com.twowasik_project.model.Media;
import com.twowasik_project.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class AddMessageDto {
    private int chat_id;
    private String user_id;
    private Date time;
    private String text;
    private Media media;
    private boolean status;
    private String who_saw;
    private boolean isFixed;
}
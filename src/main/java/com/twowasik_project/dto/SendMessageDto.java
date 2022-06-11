package com.twowasik_project.dto;

import com.twowasik_project.model.Message;
import com.twowasik_project.model.User;
import lombok.Data;

@Data
public class SendMessageDto {
    private Message message;
    private User user;

    public SendMessageDto(Message message, User user) {
        this.message = message;
        this.user = user;
    }
}
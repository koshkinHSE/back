package com.twowasik.messenger.dto;

import com.twowasik.messenger.model.Message;
import com.twowasik.messenger.model.User;
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
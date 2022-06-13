package com.twowasik.messenger.dto;

import com.twowasik.messenger.model.Message;
import com.twowasik.messenger.model.User;
import lombok.Data;

import java.util.List;

@Data
public class FindChatMessagesDto {
    private List<Message> messages;
    private List<User> users;

    public FindChatMessagesDto(List<Message> messages, List<User> users) {
        this.messages = messages;
        this.users = users;
    }
}
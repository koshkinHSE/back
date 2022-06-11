package com.twowasik_project.dto;

import com.twowasik_project.model.Message;
import com.twowasik_project.model.User;
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
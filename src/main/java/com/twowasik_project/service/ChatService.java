package com.twowasik_project.service;

import com.twowasik_project.model.Chat;
import com.twowasik_project.model.Team;

import java.util.List;

public interface ChatService {

    public Chat saveChat(Chat chat);

    public List<Chat> showChats(String chat_type);
}
package com.twowasik_project.service;

import com.twowasik_project.dto.ShowDto;
import com.twowasik_project.model.Chat;
import com.twowasik_project.model.Team;

import java.util.List;

public interface ChatService {

    public Chat saveChat(Chat chat);

    public List<Chat> showChats(int userId, String type);

    public boolean saveChannel(String name, int teamId);

    ShowDto showChannels(int teamId);
}
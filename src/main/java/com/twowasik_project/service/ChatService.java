package com.twowasik_project.service;

import com.twowasik_project.dto.ShowDto;
import com.twowasik_project.model.Chat;

public interface ChatService {

    public Chat saveChat(Chat chat);

    public boolean saveChannel(String name, int teamId);

    ShowDto showChannels(int teamId);
}
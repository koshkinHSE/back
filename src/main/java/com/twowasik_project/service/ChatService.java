package com.twowasik_project.service;

import com.twowasik_project.dto.LastMessageDto;
import com.twowasik_project.dto.ShowDto;
import com.twowasik_project.model.*;

import java.util.List;

public interface ChatService {

    Chat saveChat(Chat chat);

    LastMessageDto showChats(int userId, String type);

    boolean saveChannel(String name, int teamId);

    ShowDto showChannels(int teamId);

    Message findMassageById(int id);

    ChatRef save_ref(ChatRef chatRef);

    void dePinedMessage(int id);

    void pinMessage(int id);

    List<ChatRef> getRef(int id);
}
package com.twowasik.messenger.service;

import com.twowasik.messenger.dto.LastMessageDto;
import com.twowasik.messenger.dto.ShowDto;
import com.corporate.model.*;
import com.twowasik.messenger.model.Chat;
import com.twowasik.messenger.model.ChatRef;
import com.twowasik.messenger.model.Message;

import java.util.List;

public interface ChatService {

    Chat saveChat(Chat chat);

    LastMessageDto showChats(int userId, String type);

    int saveChannel(String name, int teamId);

    ShowDto showChannels(int teamId);

    Message findMassageById(int id);

    ChatRef save_ref(ChatRef chatRef);

    void dePinedMessage(int id);

    void pinMessage(int id);

    List<ChatRef> getRef(int id);
}
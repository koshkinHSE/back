package com.twowasik_project.service;

import com.twowasik_project.dto.ShowDto;
import com.twowasik_project.model.Chat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService{
    private final com.twowasik_project.repository.ChatRepository chatRepository;
    //private final com.twowasik_project.repository.MessageRepository messageRepository;

    @Override
    public Chat saveChat(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public boolean saveChannel(String name, int teamId) {
        if (chatRepository.checkChannel(name) != null) { return false; }
        chatRepository.save(new Chat(name, teamId));
        return true;
    }

    @Override
    public ShowDto showChannels(int teamId) {
        return new ShowDto(chatRepository.getChatsId(teamId), chatRepository.getChatsName(teamId));
    }
//
//    public Message saveMessage(Message message) {
//        return messageRepository.save(message);
//    }
}
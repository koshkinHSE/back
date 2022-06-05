package com.twowasik_project.service;

import com.twowasik_project.model.Chat;
import com.twowasik_project.model.Message;
import com.twowasik_project.repository.TeamRepository;
import com.twowasik_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService{
    private final com.twowasik_project.repository.ChatRepository chatRepository;
    //private final com.twowasik_project.repository.MessageRepository messageRepository;

//    @Override
    public Chat saveChat(Chat chat) {
        return chatRepository.save(chat);
    }
//
//    public Message saveMessage(Message message) {
//        return messageRepository.save(message);
//    }
}
package com.twowasik.messenger.service;

import com.twowasik.messenger.dto.FindChatMessagesDto;
import com.twowasik.messenger.model.Message;
import com.twowasik.messenger.model.User;
import com.twowasik.messenger.repository.ChatMessageRepository;
import com.twowasik.messenger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatMessageService {
    @Autowired private ChatMessageRepository repository;
    @Autowired private UserRepository userRepository;

    public Message save(Message chatMessage, int ref) {
        if (ref != -1) {
            chatMessage.setRef(ref);
        }
        repository.save(chatMessage);
        return chatMessage;
    }

    public FindChatMessagesDto findChatMessages(int chat_id) {
        List<Message> messages = (repository.getMessages(chat_id));
        List<User> users = new ArrayList<>();

        for (Message message: messages) {
            users.add(userRepository.findById(message.getUser_id()));
        }

        return new FindChatMessagesDto(messages, users);
    }
}
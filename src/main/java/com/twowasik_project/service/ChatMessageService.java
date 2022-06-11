package com.twowasik_project.service;

import com.twowasik_project.dto.FindChatMessagesDto;
import com.twowasik_project.model.Message;
import com.twowasik_project.model.User;
import com.twowasik_project.repository.ChatMessageRepository;
import com.twowasik_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageService {
    @Autowired private ChatMessageRepository repository;
    @Autowired private ChatRoomService chatRoomService;
    @Autowired private UserRepository userRepository;

    public Message save(Message chatMessage, int ref) {
        if (ref != -1) {
            chatMessage.setRef(ref);
        }
        repository.save(chatMessage);
        return chatMessage;
    }

//    public long countNewMessages(String senderId, String recipientId) {
//        return repository.countBySenderIdAndRecipientIdAndStatus(
//                senderId, recipientId, MessageStatus.RECEIVED);
//    }

    public FindChatMessagesDto findChatMessages(int chat_id) {
        List<Message> messages = (repository.getMessages(chat_id));
        List<User> users = new ArrayList<>();

        for (Message message: messages) {
            users.add(userRepository.findById(message.getUser_id()));
        }

        return new FindChatMessagesDto(messages, users);
    }

    public Optional<Message> findById(int id) {
        return repository.findById(id).map(chatMessage -> {
            return repository.save(chatMessage);
                });
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("can't find message (" + id + ")"));
    }


}
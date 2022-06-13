package com.corporate.service;

import com.corporate.model.Chat;
import com.corporate.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService {

    @Autowired private ChatRoomRepository chatRoomRepository;

    public Optional<Integer> getChatId(int chat_id, boolean createIfNotExist) {

        return chatRoomRepository.findById(chat_id).map(Chat::getId).or(() -> {
                    if(!createIfNotExist) {
                        return  Optional.empty();
                    }
                    return Optional.of(chat_id);
                });
    }
}

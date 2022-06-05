package com.twowasik_project.service;

import com.twowasik_project.model.Chat;
import com.twowasik_project.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService {

    @Autowired private ChatRoomRepository chatRoomRepository;

    public Optional<Integer> getChatId(int chat_id, boolean createIfNotExist) {

        return chatRoomRepository.findByChatId(chat_id).map(Chat::getId).or(() -> {
                    if(!createIfNotExist) {
                        return  Optional.empty();
                    }
                    //chatRoomRepository.save(chat_id);
                    return Optional.of(chat_id);
                });
    }
}

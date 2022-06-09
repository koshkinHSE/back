package com.twowasik_project.service;

import com.twowasik_project.dto.ShowDto;
import com.twowasik_project.model.Chat;
import com.twowasik_project.model.Message;
import com.twowasik_project.repository.ChatMessageRepository;
import com.twowasik_project.repository.TeamRepository;
import com.twowasik_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService{
    private final com.twowasik_project.repository.ChatRepository chatRepository;

    private final com.twowasik_project.service.UserService userService;
    //private final com.twowasik_project.repository.MessageRepository messageRepository;

    private final ChatMessageRepository chatMessageRepository;

    @Override
    public Chat saveChat(Chat chat) {
        return chatRepository.save(chat);
    }

    public List<Chat> showChats(int userId, String type){
        Collection chats = userService.getChats(userId);
        System.out.println(type);
        System.out.println(chats);
        List<Chat> user_chats = chatRepository.getUserChats(type, chats);
        return user_chats;
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

    @Override
    public Message findMassageById(int id) {
        return chatMessageRepository.findMessageByMessage_id(id);
    }
}
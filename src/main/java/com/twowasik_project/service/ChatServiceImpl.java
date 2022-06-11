package com.twowasik_project.service;

import com.twowasik_project.dto.LastMessageDto;
import com.twowasik_project.dto.ShowDto;
import com.twowasik_project.model.Chat;
import com.twowasik_project.model.Media;
import com.twowasik_project.model.Message;
import com.twowasik_project.repository.ChatMessageRepository;
import com.twowasik_project.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService{
    private final ChatRepository chatRepository;

    private final ChatMessageRepository chatMessageRepository;

    private final UserService userService;

    @Autowired
    private LastMessageDto lastMessageDto;

    @Override
    public Chat saveChat(Chat chat) {
        return chatRepository.save(chat);
    }

    public LastMessageDto showChats(int userId, String type){
        Collection chats = userService.getChats(userId);
        List<Chat> user_chats = chatRepository.getUserChats(type, chats);
        lastMessageDto.setChats(user_chats);
        ArrayList<Message> last_mes = new ArrayList();
        for (Chat chat: user_chats){
            last_mes.add(chatMessageRepository.getLastMessage(chat.getId()));
        }
        lastMessageDto.setMessages(last_mes);
        return lastMessageDto;
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
        return chatMessageRepository.findMessageByMessageId(id);
    }

    @Override
    public List<Media> getMedia(int id) {
        return chatRepository.getMedia(id);
    }
}
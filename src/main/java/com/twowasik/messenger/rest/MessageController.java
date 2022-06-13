package com.twowasik.messenger.rest;

import com.twowasik.messenger.dto.SendMessageDto;
import com.twowasik.messenger.model.Message;
import com.twowasik.messenger.repository.ChatRefRepository;
import com.twowasik.messenger.service.ChatMessageService;
import com.twowasik.messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
@Controller
public class MessageController {

    @Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private ChatMessageService chatMessageService;
    @Autowired private ChatRefRepository chatRefRepository;

    @Autowired private UserService userService;

    @MessageMapping("/chat")
    public void processMessage(@Payload Message chatMessage) {
        SendMessageDto sendMessageDto = new SendMessageDto(chatMessage, userService.findById(chatMessage.getUser_id()));

        Message saved = chatMessageService.save(chatMessage, -1);
        String text = chatMessage.getText();
        if (text.indexOf("@when2meet") != -1){
            String ref = new String();
            int start = text.indexOf("h", text.indexOf("@when2meet")+7);
            ref = text.substring(start, text.indexOf(" ", start));
            chatRefRepository.updateW2M(ref, chatMessage.getChat_id());
        }
        if (text.indexOf("@github") != -1){
            String ref = new String();
            int start = text.indexOf("h", text.indexOf("@github")+7);
            ref = text.substring(start, text.indexOf(" ", start));
            chatRefRepository.updateGit(ref, chatMessage.getChat_id());
        }
        if (text.indexOf("@meeting") != -1){
            String ref = new String();
            int start = text.indexOf("h", text.indexOf("@meeting"));
            ref = text.substring(start, text.indexOf(" ", start));
            chatRefRepository.updateMeeting(ref, chatMessage.getChat_id());
        }
        messagingTemplate.convertAndSendToUser(chatMessage.getChat_id() + "","queue/messages", sendMessageDto);
    }

    @GetMapping("/messages/{chat_id}")
    public ResponseEntity<?> findChatMessages (@PathVariable int chat_id) {
        return ResponseEntity.ok(chatMessageService.findChatMessages(chat_id));
    }
}
package com.twowasik_project.rest;

//import com.twowasik_project.dto.AddMessageDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import com.twowasik_project.model.Message;
//import com.twowasik_project.repository.MessageRepository;
//import com.twowasik_project.repository.ChatRepository;
//import com.twowasik_project.repository.UserRepository;
//
//import java.util.Date;
//
//import java.util.List;
//
//@Controller
//public class MessageController {
//
//    @Autowired
//    private MessageRepository MessageRepository;
//
//    @Autowired
//    private ChatRepository chatRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @RequestMapping("/chat")
//    public String chat() {
//        return "chat";
//    }
//
//    @RequestMapping(value = "/messages", method = RequestMethod.POST)
//    @MessageMapping("/newMessage")
//    @SendTo("/topic/newMessage")
//    public ResponseEntity save(@RequestBody AddMessageDto AddMessageDto) {
//        Message chatMessage = new Message(chatRepository.findById(AddMessageDto.getChat_id()), userRepository.findByUsername(AddMessageDto.getUser_id()), new Date(), AddMessageDto.getText(),AddMessageDto.getMedia());
//        System.out.println(chatMessage);
//        MessageRepository.save(chatMessage);
//        System.out.println(chatMessage);
//        List<Message> MessageList = MessageRepository.findByChat_Id(chatRepository.findById(AddMessageDto.getChat_id()).getId());
//        return new ResponseEntity(MessageList, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/messages", method = RequestMethod.GET)
//    public HttpEntity list() {
//        List<Message> MessageList = MessageRepository.findAll(PageRequest.of(0, 5, Sort.Direction.DESC, "time")).getContent();
//        System.out.println(MessageList);
//        return new ResponseEntity(MessageList, HttpStatus.OK);
//    }
//
//}

import com.twowasik_project.model.Message;
import com.twowasik_project.service.ChatMessageService;
import com.twowasik_project.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MessageController {

    @Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private ChatMessageService chatMessageService;
    @Autowired private ChatRoomService chatRoomService;

    @MessageMapping("/chat")
    public void processMessage(@Payload Message chatMessage) {
        Message saved = chatMessageService.save(chatMessage, -1);
        messagingTemplate.convertAndSend("/messages", chatMessage);
    }

//    @GetMapping("/messages/{chat_id}/count")
//    public ResponseEntity<Long> countNewMessages(@PathVariable int chat_id) {
//
//        return ResponseEntity.ok(chatMessageService.countNewMessages(chat_id));
//    }

    @GetMapping("/messages/{chat_id}")
    public ResponseEntity<?> findChatMessages (@PathVariable int chat_id) {
        return ResponseEntity.ok(chatMessageService.findChatMessages(chat_id));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage ( @PathVariable int id) {
        return ResponseEntity.ok(chatMessageService.findById(id));
    }

    @PostMapping("/replyMessage/{ref}")
    public ResponseEntity<?> replyMessage (@Payload Message chatMessage, @PathVariable int ref) {
        return ResponseEntity.ok(chatMessageService.save(chatMessage, ref));
    }
}
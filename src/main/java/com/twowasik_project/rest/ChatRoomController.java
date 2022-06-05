//package com.twowasik_project.rest;
//
//import com.twowasik_project.model.Message;
//import com.twowasik_project.dto.AddMessageDto;
//import com.twowasik_project.repository.MessageRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//import com.twowasik_project.repository.ChatRepository;
//import com.twowasik_project.repository.UserRepository;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//
//@Controller
//public class ChatRoomController {
//
//    @Autowired
//    private SimpMessagingTemplate simpMessagingTemplate;
//
//    @Autowired
//    private MessageRepository messageRepository;
//
//    @Autowired
//    private ChatRepository chatRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//
//    @MessageMapping("/message")
//    @SendTo("/chatroom/public")
//    public Message receiveMessage(@Payload Message message){
//        Message chatMessage = new Message(chatRepository.findById(AddMessageDto.getChat_id()), userRepository.findByUsername(AddMessageDto.getUser_id()), new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()), AddMessageDto.getText(),AddMessageDto.getMedia());
//        Message message1 = messageRepository.save(chatMessage);
//        List<ChatMessageModel> chatMessageModelList = chatMessageRepository.findAll(new PageRequest(0, 5, Sort.Direction.DESC, "createDate")).getContent();
//        return new ChatMessage(chatMessageModelList.toString());
//    }
//
//    @MessageMapping("/private-message")
//    public Message recMessage(@Payload Message message){
//        simpMessagingTemplate.convertAndSend(message.getChat_id(),message);
//        System.out.println(message.toString());
//        return message;
//    }
//}

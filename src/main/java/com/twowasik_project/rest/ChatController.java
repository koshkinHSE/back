package com.twowasik_project.rest;

import com.twowasik_project.dto.*;
import com.twowasik_project.exceptions.InvalidTokenExceptions;
import com.twowasik_project.jwt.JWTProvider;
import com.twowasik_project.model.*;
import com.twowasik_project.service.ChatService;
import com.twowasik_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
@RestController
@RequestMapping(value = "/chat/")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatIdDto chatIdDto;

    @Autowired
    private ShowChatsDto userIdDto;

    @PostMapping("create")
    public ResponseEntity createChat(HttpServletRequest request, @RequestBody CreateChatDto CreateChatDto) {

        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
            return ResponseEntity.badRequest().body("Unauthorized");
        }
        int team_id = 1;
        String type = CreateChatDto.getType();
        String participants = " ";
        String ava;
        if (type.equals("GROUP")){
            List<User> participants_list = CreateChatDto.getParticipants();
            participants = String.valueOf(userService.findByUsername(jwtProvider.getAccessClaims(request.getHeader("Authorization")).getSubject()).getId()).concat(" ");
            User user = userService.findByUsername(jwtProvider.getAccessClaims(request.getHeader("Authorization")).getSubject());
            String chats = user.getChats();
            for (int i = 0; i < participants_list.size(); i++){
                user = participants_list.get(i);
                String id = String.valueOf(user.getId()).concat(" ");
                participants = participants.concat(id);
            }
            ava = CreateChatDto.getAva();
        }
        else{
            participants = String.valueOf(userService.findByUsername(jwtProvider.getAccessClaims(request.getHeader("Authorization")).getSubject()).getId()).concat(" ");
            List<User> participants_list = CreateChatDto.getParticipants();
            User user = participants_list.get(0);
            ava = CreateChatDto.getAva();
            participants = participants.concat(String.valueOf(user.getId()));
        }
        String name = CreateChatDto.getName();
        chatIdDto.setId(chatService.saveChat(new Chat(name, participants, type, ava)).getId());
        userService.addChat(Integer.toString(chatIdDto.getId()), participants);
        chatService.save_ref(new ChatRef(chatIdDto.getId()));
        return ResponseEntity.ok(chatIdDto);
    }

    @PostMapping("showChats")
    public ResponseEntity ShowChats(HttpServletRequest request, @RequestBody ShowChatsDto showChatsDto) {

        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
            throw new InvalidTokenExceptions();
        }
        return ResponseEntity.ok(chatService.showChats(showChatsDto.getId(), showChatsDto.getChat_type()));
    }

    @PostMapping("createChannel")
    public ResponseEntity createChannel(HttpServletRequest request, @RequestBody CreateChannelDto createChannelDto) {

        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
            return ResponseEntity.badRequest().body("Unauthorized");
        }

        if (!chatService.saveChannel(createChannelDto.getName(), createChannelDto.getTeam_id())) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("showChannels")
    public ResponseEntity showChannels(HttpServletRequest request, @RequestBody IdDto idDto) {

        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
            throw new InvalidTokenExceptions();
        }

        return ResponseEntity.ok(chatService.showChannels(idDto.getId()));
    }

    @PatchMapping("pinned")
    public ResponseEntity getPinned(HttpServletRequest request, @RequestBody PinMessageDto pinMessageDto) {

        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
            throw new InvalidTokenExceptions();
        }

        chatService.dePinedMessage(pinMessageDto.getChat_id());

        Message message = chatService.findMassageById(pinMessageDto.getMessage());

        if (message == null) {
            return ResponseEntity.notFound().build();
        }

        chatService.pinMessage(pinMessageDto.getMessage());

        return ResponseEntity.ok(message);
    }

    @PatchMapping("dePinned")
    public void dePinned(HttpServletRequest request, @RequestBody IdDto idDto) {

        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
            throw new InvalidTokenExceptions();
        }

        chatService.dePinedMessage(idDto.getId());
    }

    @PostMapping("getMedia")
    public ResponseEntity getMedia(HttpServletRequest request, @RequestBody MediaDto MediaDto) {
        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
            throw new InvalidTokenExceptions();
        }
        int chatId = MediaDto.getId();
        return ResponseEntity.ok(chatService.getMedia(chatId));
    }
}
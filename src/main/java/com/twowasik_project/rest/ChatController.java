package com.twowasik_project.rest;

import com.twowasik_project.dto.CreateChannelDto;
import com.twowasik_project.dto.CreateChatDto;
import com.twowasik_project.dto.CreateTeamDto;
import com.twowasik_project.dto.TeamIdDto;
import com.twowasik_project.exceptions.InvalidTokenExceptions;
import com.twowasik_project.jwt.JWTProvider;
import com.twowasik_project.model.Chat;
import com.twowasik_project.model.Message;
import com.twowasik_project.model.Team;
import com.twowasik_project.model.User;
import com.twowasik_project.service.ChatService;
import com.twowasik_project.service.TeamService;
import com.twowasik_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

    @PostMapping("create")
    public ResponseEntity createChat(HttpServletRequest request, @RequestBody CreateChatDto CreateChatDto) {

        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
            return ResponseEntity.badRequest().body("Unauthorized");
        }
        String type = CreateChatDto.getType();
        String participants = " ";
        String ava;
        if (type == "GROUP"){
            List<User> participants_list = CreateChatDto.getParticipants();
            participants = String.valueOf(userService.findByUsername(jwtProvider.getAccessClaims(request.getHeader("Authorization")).getSubject()).getId()).concat(" ");
            for (int i = 0; i < participants_list.size(); i++){
                User user = participants_list.get(i);
                String id = String.valueOf(user.getId()).concat(" ");
                participants = participants.concat(id);
            }
            ava = CreateChatDto.getAva();
        }
        else{
            participants = String.valueOf(userService.findByUsername(jwtProvider.getAccessClaims(request.getHeader("Authorization")).getSubject()).getId()).concat(" ");
            List<User> participants_list = CreateChatDto.getParticipants();
            User user = participants_list.get(0);
            ava = user.getAvatar();
            participants = participants.concat(String.valueOf(user.getId()));
        }
        String name = CreateChatDto.getName();

        chatService.saveChat(new Chat(name, participants, type, ava));
        return ResponseEntity.ok(true);
    }

    @PostMapping("showChats/{chat_type}")
    public ResponseEntity ShowChats(HttpServletRequest request, @PathVariable String chat_type) {

        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
            throw new InvalidTokenExceptions();
        }
        return ResponseEntity.ok(chatService.showChats(chat_type));
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
    public ResponseEntity createChannel(HttpServletRequest request, @RequestBody TeamIdDto teamIdDto) {

        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
            throw new InvalidTokenExceptions();
        }

        return ResponseEntity.ok(chatService.showChannels(teamIdDto.getId()));
    }
}
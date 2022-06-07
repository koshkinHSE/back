package com.twowasik_project.rest;

import com.twowasik_project.dto.CreateChatDto;
import com.twowasik_project.dto.CreateTeamDto;
import com.twowasik_project.exceptions.InvalidTokenExceptions;
import com.twowasik_project.jwt.JWTProvider;
import com.twowasik_project.model.Chat;
import com.twowasik_project.model.Message;
import com.twowasik_project.model.Team;
import com.twowasik_project.model.User;
import com.twowasik_project.service.ChatService;
import com.twowasik_project.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/chat/")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private JWTProvider jwtProvider;

    @PostMapping("create")
    public ResponseEntity createChat(HttpServletRequest request, @RequestBody CreateChatDto CreateChatDto) {

        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
            return ResponseEntity.badRequest().body("Unauthorized");
        }

        String name = CreateChatDto.getName();
        String participants = "1 2";

        chatService.saveChat(new Chat(name, participants));
        return ResponseEntity.ok(true);
    }

//    @PostMapping("showChannels")
//    public ResponseEntity createChannel(HttpServletRequest request, @RequestBody ShowChannelDto showChannelDto) {
//
//        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
//            throw new InvalidTokenExceptions();
//        }
//
//        List<Integer> id = new ArrayList<>();
//        List<String> name = new ArrayList<>();
//        List<String> description = new ArrayList<>();
//
//        for(Chat channel: chatRepository.getAll(showChannelDto.getTeam_id())) {
//            id.add(channel.getId());
//            name.add(channel.getName());
//            description.add(channel.getDescription());
//        }
//        showChannel.setId(id);
//        showChannel.setName(name);
//        showChannel.setDescription(description);
//        return ResponseEntity.ok(showChannel);
//    }
}
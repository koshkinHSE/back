package com.twowasik_project.rest;

import com.twowasik_project.dto.CreateChannelDto;
import com.twowasik_project.jwt.JWTProvider;
import com.twowasik_project.model.Channel;
import com.twowasik_project.repository.TeamRepository;
import com.twowasik_project.service.ChannelService;
import com.twowasik_project.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/channels/")
public class ChannelController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private JWTProvider jwtProvider;

    @PostMapping("create")
    public ResponseEntity createChannel(HttpServletRequest request, @RequestBody CreateChannelDto createChannelDto) {

        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
            return ResponseEntity.badRequest().body("Unauthorized");
        }

        int teamId = createChannelDto.getTeamId();
        channelService.saveChannel(new Channel(createChannelDto.getName(), createChannelDto.getDescription(), teamRepository.findById(createChannelDto.getTeamId())));
        return ResponseEntity.ok(true);
    }
}
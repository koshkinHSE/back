package com.twowasik_project.rest;

import com.twowasik_project.dto.CreateChannelDto;
import com.twowasik_project.dto.ShowChannel;
import com.twowasik_project.dto.ShowChannelDto;
import com.twowasik_project.exceptions.InvalidTokenExceptions;
import com.twowasik_project.jwt.JWTProvider;
import com.twowasik_project.model.Channel;
import com.twowasik_project.repository.ChannelRepository;
import com.twowasik_project.repository.TeamRepository;
import com.twowasik_project.service.ChannelService;
import com.twowasik_project.service.TeamService;
import liquibase.pro.packaged.P;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
@RestController
@RequestMapping(value = "/channels/")
public class ChannelController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private ShowChannel showChannel;

    @PostMapping("create")
    public ResponseEntity createChannel(HttpServletRequest request, @RequestBody CreateChannelDto createChannelDto) {

        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
            throw new InvalidTokenExceptions();
        }

        int teamId = createChannelDto.getTeamId();
        channelService.saveChannel(new Channel(createChannelDto.getName(), createChannelDto.getDescription(), teamRepository.findById(createChannelDto.getTeamId())));
        return ResponseEntity.ok(true);
    }

    @PostMapping("showChannels")
    public ResponseEntity createChannel(HttpServletRequest request, @RequestBody ShowChannelDto showChannelDto) {

        if (!jwtProvider.validateAccessToken(request.getHeader("Authorization"))) {
            throw new InvalidTokenExceptions();
        }

        List<Integer> id = new ArrayList<>();
        List<String> name = new ArrayList<>();
        List<String> description = new ArrayList<>();

        for(Channel channel: channelRepository.getAll(showChannelDto.getTeam_id())) {
            id.add(channel.getId());
            name.add(channel.getName());
            description.add(channel.getDescription());
        }
        showChannel.setId(id);
        showChannel.setName(name);
        showChannel.setDescription(description);
        return ResponseEntity.ok(showChannel);
    }
}
package com.twowasik_project.service;

import com.twowasik_project.model.Channel;
import com.twowasik_project.model.Team;
import com.twowasik_project.repository.TeamRepository;
import com.twowasik_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChannelServiceImpl implements ChannelService{
    private final UserRepository userRepository;
    private final com.twowasik_project.repository.ChannelRepository ChannelRepository;

    @Override
    public Channel saveChannel(Channel channel) {
        return ChannelRepository.save(channel);
    }
}
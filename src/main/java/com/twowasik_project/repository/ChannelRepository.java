package com.twowasik_project.repository;

import com.twowasik_project.model.Channel;
import com.twowasik_project.model.Team;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Integer> {
}
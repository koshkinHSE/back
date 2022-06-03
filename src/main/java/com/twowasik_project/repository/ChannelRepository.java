package com.twowasik_project.repository;

import com.twowasik_project.model.Channel;
import com.twowasik_project.model.Team;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChannelRepository extends JpaRepository<Channel, Integer> {
    @Query(value = "SELECT * FROM channel WHERE team_id = ?", nativeQuery = true)
    List<Channel> getAll(int team_id);
}
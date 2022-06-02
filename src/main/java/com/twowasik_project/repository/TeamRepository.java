package com.twowasik_project.repository;

import com.twowasik_project.model.Team;
import com.twowasik_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    public Team findById(int team_id);
}
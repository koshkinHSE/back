package com.twowasik_project.repository;

import com.twowasik_project.model.Team;
import com.twowasik_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    public Team findById(int team_id);

    @Query(value = "SELECT * FROM team", nativeQuery = true)
    List<Team> getAll();
}
package com.twowasik_project.repository;

import com.twowasik_project.model.Chat;
import com.twowasik_project.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    public Chat findById(int chat_id);
}
package com.twowasik_project.repository;

import com.twowasik_project.model.Chat;
import com.twowasik_project.model.Message;
import com.twowasik_project.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    public Chat findById(int chat_id);

    @Query(value = "select * from chat where chat_type = :chat_type", nativeQuery = true)
    List<Chat> getChats(@Param("chat_type") String chat_type);
}
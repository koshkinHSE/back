package com.twowasik_project.repository;

import com.twowasik_project.model.Chat;
import com.twowasik_project.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<Message, Integer> {

    long countByChatId(int chat_id);

    List<Message> findByChatId(int chatId);
}

package com.twowasik_project.repository;

import com.twowasik_project.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<Chat, Integer> {
    Optional<Chat> findById(int chat_id);
}
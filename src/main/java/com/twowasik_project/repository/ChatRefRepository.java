package com.twowasik_project.repository;

import com.twowasik_project.model.Chat;
import com.twowasik_project.model.ChatRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ChatRefRepository extends JpaRepository<ChatRef, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update chat_ref set w2m = :w2m where chat_id = :chat_id", nativeQuery = true)
    void updateW2M(@Param("w2m") String w2m, @Param("chat_id") int chat_id);

    @Transactional
    @Modifying
    @Query(value = "update chat_ref set git = :git where chat_id = :chat_id", nativeQuery = true)
    void updateGit(@Param("git") String git, @Param("chat_id") int chat_id);

    @Transactional
    @Modifying
    @Query(value = "update chat_ref set meeting = :meeting where chat_id = :chat_id", nativeQuery = true)
    void updateMeeting(@Param("meeting") String meeting, @Param("chat_id") int chat_id);
}

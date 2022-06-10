package com.twowasik_project.repository;

import com.twowasik_project.model.Chat;
import com.twowasik_project.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<Message, Integer> {

    //long countByChatid(int chatid);

    @Query(value = "select * from message where chat_id = :chat_id", nativeQuery = true)
    List<Message> getMessages(@Param("chat_id") int chat_id);

    Message findMessageByMessageId(int message_id);

    @Query(value = "select * from message where chat_id = :chat_id ORDER BY message.message_id DESC LIMIT 1", nativeQuery = true)
    Message getLastMessage(@Param("chat_id") int chat_id);
}

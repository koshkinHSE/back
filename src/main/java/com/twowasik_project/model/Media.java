package com.twowasik_project.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "channel")
@Data
public class Media {
    private static int newId = 0;

    @Id
    @Column(name = "media_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private Chat chat_id;

    public Media() {

    }
    public Media(String name, String participants, Chat chat) {
        this.content = content;
        this.chat_id = chat;
    }
}
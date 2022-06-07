package com.twowasik_project.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "media")
@Data
public class Media {
    private static int newId = 0;

    @Id
    @Column(name = "media_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "content")
    private String content;

    @JoinColumn(name = "chat_id", referencedColumnName = "chat_id")
    private int chat_id;

    public Media() {

    }
    public Media(String name, String participants, int chat) {
        this.content = content;
        this.chat_id = chat;
    }
}
package com.twowasik_project.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "chat")
@Data
public class Chat {
    private static int newId = 0;

    @Id
    @Column(name = "chat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "chat_name")
    private String name;

    @Column(name = "chat_participants")
    private String participants;

    @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    private int teamId;

    public Chat() {
    }

    public Chat(String name, String participants) {
        this.name = name;
        this.participants = participants;
    }
}
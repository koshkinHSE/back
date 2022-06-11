package com.twowasik_project.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


@Entity
@Table(name = "message")
@Data
public class Message {

    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;

    @JoinColumn(name = "chat_id", referencedColumnName = "chat_id")
    private int chat_id;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private int user_id;

    @Column(name = "time")
    private Time time;

    @Column(name = "text")
    private String text;

    @JoinColumn(name = "media_id", referencedColumnName = "media_id")
    private int media_id;

    @Column(name = "status")
    private boolean status;

    @Column(name = "is_fixed")
    private boolean isFixed;

    @Column(name = "ref")
    private Integer ref;

    public Message() {
    }

    public Message(int chat, int user, Time time, String text, int media, int ref) {
        this.chat_id = chat;
        this.user_id = user;
        this.time = time;
        this.text = text;
        this.status = false;
        this.media_id = media;
        this.isFixed = false;
        this.ref = ref;
    }
}
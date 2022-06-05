package com.twowasik_project.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "message")
@Data
public class Message {

    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int message_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private Chat chat_id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "time")
    private Date time;

    @Column(name = "text")
    private String text;

    @OneToOne
    @JoinColumn(name = "media_id", referencedColumnName = "media_id")
    private Media media;

    @Column(name = "status")
    private boolean status;

    @Column(name = "who_saw")
    private String who_saw;

    @Column(name = "isFixed")
    private boolean isFixed;

    public Message() {
    }

    public Message(Chat chat, User user, Date time, String text, Media media) {
        this.chat_id = chat;
        this.user = user;
        this.time = time;
        this.text = text;
        this.status = false;
        this.media = media;
        this.isFixed = false;
    }
}
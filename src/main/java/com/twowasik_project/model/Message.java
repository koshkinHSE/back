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

    @JoinColumn(name = "chat_id", referencedColumnName = "chat_id")
    private int chat_id;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private int userId;

    @Column(name = "time")
    private Date time;

    @Column(name = "text")
    private String text;

//    @JoinColumn(name = "media_id", referencedColumnName = "media_id")
//    private int media_id;

    @Column(name = "status")
    private boolean status;

    @Column(name = "who_saw")
    private String who_saw;

//    @Column(name = "isFixed")
//    private boolean isFixed;

    public Message() {
    }
}
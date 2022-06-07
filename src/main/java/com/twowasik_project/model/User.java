package com.twowasik_project.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
@Component
public class User {

    private static int newId = 0;

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @CreatedDate
    @Column(name = "created")
    private Date created;

    @CreatedDate
    @Column(name = "updated")
    private Date updated;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "teams")
    private String teams;

    @Column(name = "chats")
    private String chats;

    public User() {

    }

    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
        long createTime = System.currentTimeMillis();
        this.created = new Date(createTime);
        this.updated = new Date(createTime);
        this.status = Status.ACTIVE;
    }
}
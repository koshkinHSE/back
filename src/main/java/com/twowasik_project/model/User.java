package com.twowasik_project.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "id_user", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "id_role", referencedColumnName = "role_id")})
    private List<Role> roles;

    public User() {

    }

    public User(String email, String password, String username, List<Role> roles) {
        this.email = email;
        this.password = password;
        this.username = username;
        long createTime = System.currentTimeMillis();
        this.created = new Date(createTime);
        this.updated = new Date(createTime);
        this.roles = roles;
        this.status = Status.ACTIVE;
    }
}
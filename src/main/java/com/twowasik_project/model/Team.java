package com.twowasik_project.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "team")
@Data
public class Team {
    private static int newId = 0;

    @Id
    @Column(name = "team_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "team_name")
    private String name;

    @Column(name = "team_participants")
    private String participants;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private List<User> admins;

    public Team() {

    }

    public Team(String name, String participants, List<User> admins, User admin) {
        this.name = name;
        this.participants = participants;
        this.admins = admins;
        this.admins.add(admin);
    }
}
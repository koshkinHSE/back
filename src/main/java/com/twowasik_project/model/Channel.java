package com.twowasik_project.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "channel")
@Data
public class Channel {
    private static int newId = 0;

    @Id
    @Column(name = "channel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "channel_name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Channel() {

    }
    public Channel(String name, String description, Team team) {
        this.name = name;
        this.description = description;
        this.team = team;
    }
}
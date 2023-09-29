package com.example.bai1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @Column(unique = true)
    private String name;
    @JsonBackReference
    @OneToMany(mappedBy = "team")
    private Set<Player> playerSet;

    public Team() {
    }

    public Team(int id, String name, Set<Player> playerSet) {
        this.id = id;
        this.name = name;
        this.playerSet = playerSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Player> getPlayerSet() {
        return playerSet;
    }

    public void setPlayerSet(Set<Player> playerSet) {
        this.playerSet = playerSet;
    }
}

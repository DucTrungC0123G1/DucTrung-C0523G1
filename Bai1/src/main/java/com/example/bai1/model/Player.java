package com.example.bai1.model;

import javax.persistence.*;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String codePlayer;
    private String name;
    @Column(columnDefinition = "DATE")
    private String birthDay;
    private String exp;
    private String picture;
    private boolean status;
    private boolean action;
    @ManyToOne
    @JoinColumn(name = "position_id",referencedColumnName = "id")
    private Position position;
    @ManyToOne
    @JoinColumn(name = "team_id",referencedColumnName = "id")
    private Team team;

    public Player() {
    }

    public Player(int id, String codePlayer, String name, String birthDay, String exp, String picture, Position position, boolean status) {
        this.id = id;
        this.codePlayer = codePlayer;
        this.name = name;
        this.birthDay = birthDay;
        this.exp = exp;
        this.picture = picture;
        this.position = position;
        this.status = status;
    }

    public Player(int id, String codePlayer, String name, String birthDay, String exp, String picture, boolean status, Position position, Team team) {
        this.id = id;
        this.codePlayer = codePlayer;
        this.name = name;
        this.birthDay = birthDay;
        this.exp = exp;
        this.picture = picture;
        this.status = status;
        this.position = position;
        this.team = team;
    }

    public Player(int id, String codePlayer, String name, String birthDay, String exp, String picture, boolean status, boolean action, Position position, Team team) {
        this.id = id;
        this.codePlayer = codePlayer;
        this.name = name;
        this.birthDay = birthDay;
        this.exp = exp;
        this.picture = picture;
        this.status = status;
        this.action = action;
        this.position = position;
        this.team = team;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodePlayer() {
        return codePlayer;
    }

    public void setCodePlayer(String codePlayer) {
        this.codePlayer = codePlayer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }
}

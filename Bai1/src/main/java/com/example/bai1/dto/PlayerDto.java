package com.example.bai1.dto;

import com.example.bai1.model.Position;
import com.example.bai1.model.Team;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class PlayerDto implements Validator {
    private String codePlayer;
    private String name;
    private String birthDay;
    private String exp;
    private String picture;
    private boolean status;
    private Position position;
    private Team team;

    public PlayerDto() {
    }

    public PlayerDto(String codePlayer, String name, String birthDay, String exp, String picture, boolean status, Position position, Team team) {
        this.codePlayer = codePlayer;
        this.name = name;
        this.birthDay = birthDay;
        this.exp = exp;
        this.picture = picture;
        this.status = status;
        this.position = position;
        this.team = team;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        PlayerDto playerDto = (PlayerDto) target;
        LocalDate localDate = LocalDate.parse(playerDto.getBirthDay());
        LocalDate dateNow = LocalDate.now();
        LocalDate birthDay = LocalDate.ofEpochDay(localDate.toEpochDay());
        Period age = Period.between(birthDay, dateNow);
        if (playerDto.getCodePlayer().equals("")) {
            errors.rejectValue("codePlayer", null, "Do not Empty");
        } else if (!playerDto.getCodePlayer().matches("^[A-Z]{2,3}-[0-9]{2}$")) {
            errors.rejectValue("codePlayer", null, "Format example: XX-00");
        }
        if (playerDto.getName().equals("")) {
            errors.rejectValue("name", null, "Do not Empty");
        } else if (!playerDto.getName().matches("^(([A-Z][a-z]+) )+([A-Z][a-z]+){1}$")) {
            errors.rejectValue("name", null, "Format example: Nguyen Duc Trung");
        }
        if (age.getYears() < 16) {
            errors.rejectValue("birthDay", "Invalid", "Age must than 16 year old");
        } else if (playerDto.getBirthDay() != null) {
            if (localDate.isAfter(dateNow)) {
                errors.rejectValue("birthDay", "Invalid", "The date of birth cannot be after the current date");
            } else if (localDate.getYear() < 1923) {
                errors.rejectValue("birthDay","Invalid","Must not be more than 100 years old");
            }
        }
        if (playerDto.getExp().equals("")){
            errors.rejectValue("exp",null,"Do not empty");
        } else if (!playerDto.getExp().matches("^[1-9]{1,2}$")) {
            errors.rejectValue("exp",null,"Experience is a Integer");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerDto playerDto = (PlayerDto) o;
        return status == playerDto.status && Objects.equals(codePlayer, playerDto.codePlayer) && Objects.equals(name, playerDto.name) && Objects.equals(birthDay, playerDto.birthDay) && Objects.equals(exp, playerDto.exp) && Objects.equals(picture, playerDto.picture) && Objects.equals(position, playerDto.position) && Objects.equals(team, playerDto.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codePlayer, name, birthDay, exp, picture, status, position, team);
    }
}

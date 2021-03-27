package com.decathlon.alert.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int developerId;
    private String name;
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name="team_id")
    @JsonIgnore
    private Team team;

    public Developer() {
    }

    public Developer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Developer(int developerId, String name, String phoneNumber, Team team) {
        this.developerId = developerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.team = team;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

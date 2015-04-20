package com.tdt4240.RawHeroes.network.server.serverConnection.player;

import java.io.Serializable;

/**
 * Created by espen1 on 03.03.2015.
 */
public class Player implements Serializable{
    private String username;
    private String password;
    private PlayerTypes playerType;
    private Child child;
    private int score;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        score = 0;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password){ this.password = password;}

    public void setUsername(String username) {
        this.username = username;
    }

    public PlayerTypes getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerTypes playerType) {
        this.playerType = playerType;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public void increaseScore() {
        score += 100;
    }
}

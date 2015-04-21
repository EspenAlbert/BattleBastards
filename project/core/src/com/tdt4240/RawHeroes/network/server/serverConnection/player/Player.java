package com.tdt4240.RawHeroes.network.server.serverConnection.player;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by espen1 on 03.03.2015.
 */
public class Player implements Serializable{
    private String username;
    private String password;
    private int score;
    private ArrayList<Player> friendList = new ArrayList<Player>();

    public ArrayList<Player> getFriendList() {
        return friendList;
    }


    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        score = 0;
    }

    public boolean addToFriendList(Player player){
        int i = 0;
        for(Player p : friendList){
            if(p.getUsername().equals(player.getUsername())){
                i++;
            }
        }
        if(i == 0){
            friendList.add(player);
            return true;
        }
        return false;
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


    public void increaseScore() {
        score += 100;
    }
}

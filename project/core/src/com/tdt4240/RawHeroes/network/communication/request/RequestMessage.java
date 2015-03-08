package com.tdt4240.RawHeroes.network.communication.request;

import com.tdt4240.RawHeroes.network.server.serverConnection.player.Player;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by espen1 on 05.03.2015.
 */
public class RequestMessage implements Serializable{
    private final RequestTypes type;
    private ArrayList<Object> parameters;
    private final Player player;

    public RequestMessage(RequestTypes type, Player player) {
        this.type = type;
        this.player = player;
        parameters = new ArrayList<Object>();
    }

    public RequestTypes getType() {
        return type;
    }

    public ArrayList<Object> getParameters() {
        return parameters;
    }
    public void addParameter(Object object) {
        parameters.add(object);
    }

    public Player getPlayer() {
        return player;
    }
}

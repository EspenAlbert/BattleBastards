package com.tdt4240.RawHeroes.network.server.serverConnection.player;

import java.io.Serializable;

/**
 * Created by espen1 on 04.03.2015.
 */
public class Child implements Serializable {
    private final String name;

    public Child(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }
}
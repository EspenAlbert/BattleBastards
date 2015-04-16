package com.tdt4240.RawHeroes.event.events;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by espen1 on 27.02.2015.
 */
public abstract class BoardEvent {

    private final Vector2 position;

    public BoardEvent(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }
}

package com.tdt4240.RawHeroes.logic.events;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by espen1 on 27.02.2015.
 */
public abstract class BoardEvent {

    private final Vector2 posision;

    public BoardEvent(Vector2 posision) {
        this.posision = posision;
    }

    public Vector2 getPosision() {
        return posision;
    }
}

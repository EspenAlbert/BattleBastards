package com.tdt4240.RawHeroes.event.events;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.independent.Position;

/**
 * Created by espen1 on 27.02.2015.
 */
public abstract class BoardEvent {

    private final Position position;

    public BoardEvent(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}

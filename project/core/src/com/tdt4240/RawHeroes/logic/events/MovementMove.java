package com.tdt4240.RawHeroes.logic.events;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by espen1 on 27.02.2015.
 */
public class MovementMove extends BoardEvent {
    public MovementMove(Vector2 posision) {
        super(posision);
    }
}

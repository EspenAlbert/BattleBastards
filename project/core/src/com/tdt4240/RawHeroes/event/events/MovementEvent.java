package com.tdt4240.RawHeroes.event.events;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.event.move.MovementMove;

/**
 * Created by espen1 on 27.02.2015.
 */
public class MovementEvent extends BoardEvent {
    private final MovementMove move;

    public MovementEvent(Vector2 posision, MovementMove move)
    {
        super(posision);
        this.move = move;
    }
}

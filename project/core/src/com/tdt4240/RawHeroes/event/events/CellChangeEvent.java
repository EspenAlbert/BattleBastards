package com.tdt4240.RawHeroes.event.events;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by espen1 on 27.02.2015.
 */
public class CellChangeEvent extends BoardEvent {
    public CellChangeEvent(Vector2 posision) {
        super(posision);
    }
}

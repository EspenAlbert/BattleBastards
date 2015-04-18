package com.tdt4240.RawHeroes.event.events;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.independent.Position;

/**
 * Created by espen1 on 27.02.2015.
 */
public class CellChangeEvent extends BoardEvent {
    public CellChangeEvent(Position position) {
        super(position);
    }
}

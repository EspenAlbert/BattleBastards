package com.tdt4240.RawHeroes.logic.cell;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.logic.unit.IUnit;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface ICell {
    Vector2 getPos();
    IUnit getUnit();
}

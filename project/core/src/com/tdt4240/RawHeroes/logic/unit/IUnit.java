package com.tdt4240.RawHeroes.logic.unit;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface IUnit {
    UnitName getIdentifier();
    Vector2[] getInflictionZone(Vector2 myPos, Vector2 target, int movesLeft);
    Vector2[] getMovementZone(Vector2 myPos, int movesLeft);
    int[] inflictDamage(Vector2 myPos, Vector2[] enemies);
    void attacked(int damage);
    void deAttacked(int damage);
    boolean myPlayer();

}

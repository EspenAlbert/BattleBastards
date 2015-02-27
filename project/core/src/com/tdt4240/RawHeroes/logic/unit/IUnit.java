package com.tdt4240.RawHeroes.logic.unit;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface IUnit {
    UnitName getIdentifier();
    ArrayList<Vector2> getInflictionZone(Vector2 myPos, Vector2 target);
    ArrayList<Vector2> getMovementZone(Vector2 myPos, int movesLeft);
    int[] inflictDamage(Vector2 myPos, Vector2[] enemies);
    void attacked(int damage);
    void deAttacked(int damage);
    boolean myPlayer();

    ArrayList<Vector2> getAttackablePosisions(Vector2 pos, int movesLeft);
}

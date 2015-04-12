package com.tdt4240.RawHeroes.createUnits.units.standardUnit;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;

import java.util.ArrayList;

/**
 * Created by espen1 on 07.03.2015.
 */
public class StandardUnit implements IUnit {

    private boolean player1Unit;

    public StandardUnit(boolean player1Unit) {
        this.player1Unit = player1Unit;
        System.out.println("Created a standard unit");
    }

    @Override
    public UnitName getIdentifier() {
        return UnitName.STANDARD_UNIT;
    }

    @Override
    public ArrayList<Vector2> getInflictionZone(Vector2 myPos, Vector2 target) {
        return null;
    }

    @Override
    public ArrayList<Vector2> getMovementZone(Vector2 myPos, int movesLeft) {
        return null;
    }

    @Override
    public int[] inflictDamage(Vector2 myPos, Vector2[] enemies) {
        return new int[0];
    }

    @Override
    public void attacked(int damage) {

    }

    @Override
    public void deAttacked(int damage) {

    }

    @Override
    public boolean isPlayer1Unit() {
        return player1Unit;
    }

    @Override
    public ArrayList<Vector2> getAttackablePositions(Vector2 pos, int movesLeft) {
        return null;
    }

    @Override
    public int getHealth() {
        return 0;
    }
}

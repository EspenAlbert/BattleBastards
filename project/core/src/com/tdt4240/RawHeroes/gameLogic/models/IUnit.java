package com.tdt4240.RawHeroes.gameLogic.models;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitAttackController;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface IUnit extends Serializable{
    UnitName getIdentifier();
    ArrayList<Vector2> getInflictionZone(Vector2 myPos, Vector2 target);
    ArrayList<Vector2> getMovementZone(Vector2 myPos, int movesLeft);
    int inflictDamage(Vector2 myPos, Vector2 enemies);
    int attacked(int damage);
    void setAttackLogic(IUnitAttackController controller);
    void setHasAttacked();
    boolean isPlayer1Unit();

    ArrayList<Vector2> getAttackablePositions(Vector2 pos, int movesLeft);

    int getHealth();
}

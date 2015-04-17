package com.tdt4240.RawHeroes.gameLogic.models;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitCombatController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitMovementController;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;
import com.tdt4240.RawHeroes.independent.Position;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface IUnit extends Serializable{
    UnitName getIdentifier();
    ArrayList<Position> getInflictionZone(Position myPos, Position target);
    ArrayList<Position> getMovementZone(IBoard board, Position myPos, int movesLeft);
    ArrayList<Position> getMovementPath(IBoard board, Position myPos, Position targetPos);
    int inflictDamage(Position myPos, Position enemies);
    int attacked(int damage);
    void setAttackLogic(IUnitCombatController controller);
    void setMovementLogic(IUnitMovementController controller);
    void setHasAttacked();
    boolean isPlayer1Unit();

    ArrayList<Position> getAttackablePositions(Position pos, int movesLeft, IBoard board);

    int getHealth();
}

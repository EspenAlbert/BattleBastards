package com.tdt4240.RawHeroes.gameLogic.controllers.unitController;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.independent.Position;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by espen1 on 12.04.2015.
 */
public interface IUnitCombatController extends Serializable{
    ArrayList<Position> getAttackablePositions(Position pos, int movesLeft, IBoard board);
    ArrayList<Position> getInflictionZone(Position myPos, Position target);
    int inflictDamage();

    int attacked(int damage);

    int getMinAttackDmg();
    int getMaxAttackDmg();
    int getArmor();
}

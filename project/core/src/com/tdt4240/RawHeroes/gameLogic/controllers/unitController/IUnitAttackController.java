package com.tdt4240.RawHeroes.gameLogic.controllers.unitController;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

import java.util.ArrayList;

/**
 * Created by espen1 on 12.04.2015.
 */
public interface IUnitAttackController {
    ArrayList<Vector2> getAttackablePositions(Vector2 pos, int movesLeft);
    ArrayList<Vector2> getInflictionZone(Vector2 myPos, Vector2 target);
    int inflictDamage(Vector2 myPos, Vector2 attackPos);
}

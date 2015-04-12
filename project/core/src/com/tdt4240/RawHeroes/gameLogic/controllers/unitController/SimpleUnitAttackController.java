package com.tdt4240.RawHeroes.gameLogic.controllers.unitController;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

import java.util.ArrayList;

/**
 * Created by espen1 on 12.04.2015.
 */
public class SimpleUnitAttackController implements IUnitAttackController {
    @Override
    public ArrayList<Vector2> getAttackablePositions(IBoard board, Vector2 pos, int movesLeft) {
        return null;
    }

    @Override
    public ArrayList<Vector2> getInflictionZone(IBoard board, Vector2 myPos, Vector2 target) {
        return null;
    }

    @Override
    public int inflictDamage(Vector2 myPos, Vector2 attackPos) {
        return 0;
    }
}

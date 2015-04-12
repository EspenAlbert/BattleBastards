package com.tdt4240.RawHeroes.gameLogic.controllers.unitController;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

import java.util.ArrayList;

/**
 * Created by espen1 on 12.04.2015.
 */
public class WalkingUnitMovementController implements IUnitMovementController {
    @Override
    public ArrayList<Vector2> getMovementZone(IBoard board, Vector2 myPos, int movesLeft, int unitMaxMoves) {
        return null;
    }

    @Override
    public ArrayList<Vector2> getMovementPath(IBoard board, Vector2 myPos, Vector2 targetPos) {
        return null;
    }
}

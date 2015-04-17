package com.tdt4240.RawHeroes.gameLogic.controllers.unitController;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.independent.Position;

import java.util.ArrayList;

/**
 * Created by espen1 on 12.04.2015.
 */
public interface IUnitMovementController {
    ArrayList<Position> getMovementZone(IBoard board, Position myPos, int movesLeft, int unitMaxMoves);
    ArrayList<Position> getMovementPath(IBoard board, Position myPos, Position targetPos);
}

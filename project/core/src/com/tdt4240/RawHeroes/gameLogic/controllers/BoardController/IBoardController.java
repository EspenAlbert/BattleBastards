package com.tdt4240.RawHeroes.gameLogic.controllers.boardController;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.event.move.Move;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface IBoardController {
    void cellTouched(Vector2 coordinates);
    void cellTouchedLong(Vector2 coordinates);
    void actionButtonTouched();
    void addMove(Move move);
    void setState(BoardControllerState state);
}

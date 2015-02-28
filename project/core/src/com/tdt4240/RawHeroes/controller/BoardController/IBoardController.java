package com.tdt4240.RawHeroes.controller.BoardController;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.logic.move.Move;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface IBoardController {
    void cellTouched(Vector2 coordinates);
    void attackButtonTouched();
    void addMove(Move move);
    void setState(BoardControllerState state);
}

package com.tdt4240.RawHeroes.controller;

import com.tdt4240.RawHeroes.logic.move.IMoveListener;
import com.tdt4240.RawHeroes.logic.move.Move;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface IBoardMover {

    void executeMoves(ArrayList<Move> lastMoves);
    void add(Move move);
    void undo();
    void addMoveListener(IMoveListener listener);

}

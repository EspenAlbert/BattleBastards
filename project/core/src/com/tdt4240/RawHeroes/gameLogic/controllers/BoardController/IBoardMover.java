package com.tdt4240.RawHeroes.gameLogic.controllers.BoardController;

import com.tdt4240.RawHeroes.event.listener.IMoveListener;
import com.tdt4240.RawHeroes.event.move.Move;

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

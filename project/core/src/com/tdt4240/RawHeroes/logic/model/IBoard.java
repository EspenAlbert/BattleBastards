package com.tdt4240.RawHeroes.logic.model;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.logic.cell.ICell;
import com.tdt4240.RawHeroes.logic.events.BoardEvent;
import com.tdt4240.RawHeroes.logic.modelListener.IBoardListener;
import com.tdt4240.RawHeroes.logic.move.Move;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface IBoard {
    void addBoardListener(IBoardListener listener);
    void fireBoardChange(BoardEvent event);
    void doMove(Move move);
    ICell getCell(Vector2 pos);
    void selectPiece(Vector2 pos);
    ICell[][] getCells();
}

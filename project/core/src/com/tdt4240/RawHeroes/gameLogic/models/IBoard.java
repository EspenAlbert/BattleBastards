package com.tdt4240.RawHeroes.gameLogic.models;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.event.listener.ICellListener;
import com.tdt4240.RawHeroes.gameLogic.cell.CellStatus;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.event.events.BoardEvent;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface IBoard {
    void addBoardListener(ICellListener listener);
    void fireBoardChange(BoardEvent event);
    ICell getCell(Vector2 pos);
    ICell[][] getCells();
    void switchModeOnCell(CellStatus status);



}

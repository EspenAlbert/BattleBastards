package com.tdt4240.RawHeroes.gameLogic.models;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.event.listener.IBoardListener;
import com.tdt4240.RawHeroes.gameLogic.cell.CellStatus;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.event.events.BoardEvent;
import com.tdt4240.RawHeroes.independent.Position;

import java.io.Serializable;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface IBoard extends Serializable{
    void addBoardListener(IBoardListener listener);
    void fireBoardChanged(BoardEvent event);
    ICell getCell(Position pos);
    ICell[][] getCells();
    void switchModeOnCell(Position pos, CellStatus status);
    int getWidth();
    int getHeight();

    IBoard deepCopy();

}

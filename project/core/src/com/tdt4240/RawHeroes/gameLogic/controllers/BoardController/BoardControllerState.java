package com.tdt4240.RawHeroes.gameLogic.controllers.boardController;

import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

/**
 * Created by espen1 on 28.02.2015.
 */
public abstract class BoardControllerState {
    protected final IBoardController boardController;
    protected IBoard board;

    public abstract void actionButtonPressed();
    public abstract void cellSelected(ICell cell);
    public abstract BoardControllerStateEvent getEvent();

    public BoardControllerState(IBoardController boardController, IBoard board) {
        this.boardController = boardController;
        this.board = board;
    }

    public abstract void popped();
}

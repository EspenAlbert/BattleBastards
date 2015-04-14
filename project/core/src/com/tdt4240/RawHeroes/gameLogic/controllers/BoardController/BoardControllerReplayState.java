package com.tdt4240.RawHeroes.gameLogic.controllers.boardController;

import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

/**
 * Created by Endre on 14.04.2015.
 */
public class BoardControllerReplayState extends BoardControllerState{
    public BoardControllerReplayState(IBoardController boardController, IBoard board) {
        super(boardController, board);
    }

    @Override
    public void actionButtonPressed() { //Skip button

    }

    @Override
    public void cellSelected(ICell cell) {

    }

    @Override
    public void popped() {

    }
}


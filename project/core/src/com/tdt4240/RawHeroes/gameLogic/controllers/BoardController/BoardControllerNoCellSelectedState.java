package com.tdt4240.RawHeroes.gameLogic.controllers.BoardController;

import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

/**
 * Created by espen1 on 28.02.2015.
 */
public class BoardControllerNoCellSelectedState extends BoardControllerState {
    public BoardControllerNoCellSelectedState(IBoardController boardController, IBoard board) {
        super(boardController, board);
    }

    @Override
    public void attackButtonPressed() {

    }

    @Override
    public void cellSelected(ICell cell) {

    }

    @Override
    public void poped() {

    }
}

package com.tdt4240.RawHeroes.gameLogic.controllers.boardController;

import com.tdt4240.RawHeroes.gameLogic.cell.ICell;

/**
 * Created by espen1 on 28.02.2015.
 */
public class BoardControllerCellSelectedState extends BoardControllerState {
    private ICell selectedCell;
    public BoardControllerCellSelectedState(IBoardController boardController, ICell cell) {
        super(boardController);
        selectedCell = cell;
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

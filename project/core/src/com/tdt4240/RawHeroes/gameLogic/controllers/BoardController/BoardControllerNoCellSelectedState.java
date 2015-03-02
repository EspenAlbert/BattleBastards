package com.tdt4240.RawHeroes.gameLogic.controllers.boardController;

import com.tdt4240.RawHeroes.gameLogic.cell.ICell;

/**
 * Created by espen1 on 28.02.2015.
 */
public class BoardControllerNoCellSelectedState extends BoardControllerState {
    public BoardControllerNoCellSelectedState(IBoardController boardController) {
        super(boardController);
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

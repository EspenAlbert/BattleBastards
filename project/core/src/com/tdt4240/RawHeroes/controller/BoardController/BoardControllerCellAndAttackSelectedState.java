package com.tdt4240.RawHeroes.controller.BoardController;

import com.tdt4240.RawHeroes.logic.cell.ICell;

/**
 * Created by espen1 on 28.02.2015.
 */
public class BoardControllerCellAndAttackSelectedState extends BoardControllerState {
    private ICell selectedCell;
    public BoardControllerCellAndAttackSelectedState(IBoardController boardController, ICell cell) {
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

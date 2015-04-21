package com.tdt4240.RawHeroes.gameLogic.controllers.boardController;

import com.tdt4240.RawHeroes.gameLogic.cell.Cell;
import com.tdt4240.RawHeroes.gameLogic.cell.CellStatus;
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
    public void actionButtonPressed() {//Undo button
        this.boardController.undoMove();
    }

    @Override
    public void cellSelected(ICell cell) {
        if (cell.getUnit() != null && cell.getUnit().isPlayer1Unit() == boardController.iAmPlayer1() ){
            this.boardController.setState(new BoardControllerCellSelectedState(this.boardController, this.board, cell));
        }
    }

    @Override
    public BoardControllerStateEvent getEvent() {
        return new BoardControllerStateEvent(0, "Undo");
    }

    @Override
    public void popped() {

    }
}

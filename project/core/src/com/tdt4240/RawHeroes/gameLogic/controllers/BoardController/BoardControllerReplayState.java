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
        //TODO trenger en quick do move funksjon som gjør alle moves i lista uten å se det på skjerm
        // kan kanskje gjøres i popped()
        BoardController.resetUnitAttacks(board);
        this.boardController.setState(new BoardControllerNoCellSelectedState(this.boardController, this.board));
    }

    @Override
    public void cellSelected(ICell cell) {

    }

    @Override
    public BoardControllerStateEvent getEvent() {
        //TODO finne en bedre måte å gjøre energy parameteren på
        return new BoardControllerStateEvent(0, "Skip");
    }

    @Override
    public void popped() {

    }
}


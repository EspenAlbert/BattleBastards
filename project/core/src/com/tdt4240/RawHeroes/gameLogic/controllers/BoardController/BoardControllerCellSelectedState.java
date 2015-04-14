package com.tdt4240.RawHeroes.gameLogic.controllers.boardController;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.cell.CellStatus;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

import java.util.ArrayList;

/**
 * Created by espen1 on 28.02.2015.
 */
public class BoardControllerCellSelectedState extends BoardControllerState {
    private ICell selectedCell;
    public BoardControllerCellSelectedState(IBoardController boardController, IBoard board, ICell cell) {
        super(boardController, board);
        selectedCell = cell;
        /*
        ArrayList<Vector2> attackablePosisions = cell.getUnit().getAttackablePositions(cell.getPos(), //TODO: Moves left);
        for(Vector2 attackPos : attackablePosisions) {
            if(attackPos.x > board.getCells().length)
            board.getCell(attackPos).setStatus(CellStatus.ATTACKABLE);
        }
        */
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

package com.tdt4240.RawHeroes.createGame.winConditions;

import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

/**
 * Created by espen1 on 07.03.2015.
 */
public class NoEnemyUnitLeftWinCondition implements IWinCondition {
    @Override
    public boolean player1IsWinner(IBoard board) {
        if (findAUnitThatIsPlayer1(board, false)) return false;
        return true;
    }

    private boolean findAUnitThatIsPlayer1(IBoard board, boolean player1) {
        ICell[][] cells = board.getCells();
        for(ICell[] column: cells) {
            for(ICell cell: column) {
                if(cell.getUnit() != null && player1 == cell.getUnit().isPlayer1Unit()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean player2IsWinner(IBoard board) {
        if(findAUnitThatIsPlayer1(board, true)) return false;
        return true;
    }
}

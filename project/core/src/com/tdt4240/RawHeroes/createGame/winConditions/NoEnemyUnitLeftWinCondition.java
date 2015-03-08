package com.tdt4240.RawHeroes.createGame.winConditions;

import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

/**
 * Created by espen1 on 07.03.2015.
 */
public class NoEnemyUnitLeftWinCondition implements IWinCondition {
    @Override
    public boolean player1IsWinner(IBoard board) {
        //TODO
        return false;
    }

    @Override
    public boolean player2IsWinner(IBoard board) {
        return false;
    }
}

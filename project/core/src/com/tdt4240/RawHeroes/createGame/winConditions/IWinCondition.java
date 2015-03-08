package com.tdt4240.RawHeroes.createGame.winConditions;

import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

import java.io.Serializable;

/**
 * Created by espen1 on 07.03.2015.
 */
public interface IWinCondition extends Serializable{
    boolean player1IsWinner(IBoard board);
    boolean player2IsWinner(IBoard board);
}

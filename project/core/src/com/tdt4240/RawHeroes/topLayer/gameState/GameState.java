package com.tdt4240.RawHeroes.topLayer.gameState;

import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.event.move.Move;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public abstract class GameState {
    int id;
    String player1Nickname, player2Nickname;
    IBoard board;
    ArrayList<Move> lastMoves;
    boolean nextTurnIsPlayer1;
    private int moveCount;

    public GameState(int id, String player1Nickname, String player2Nickname) {
        this.id = id;
        this.player1Nickname = player1Nickname;
        this.player2Nickname = player2Nickname;
    }

    public String getPlayer1Nickname() {
        return player1Nickname;
    }

    public IBoard getBoard() {
        return board;
    }

    public ArrayList<Move> getLastMoves() {
        return lastMoves;
    }

    public int getMoveCount() {
        return moveCount;
    }
}

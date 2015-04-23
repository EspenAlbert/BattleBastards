package com.tdt4240.RawHeroes.createGame.factory;

import com.tdt4240.RawHeroes.createGame.boards.BoardType;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

/**
 * Created by espen1 on 07.03.2015.
 */
public interface IBoardFactory {
    IBoard getBoard(BoardType boardName);
}

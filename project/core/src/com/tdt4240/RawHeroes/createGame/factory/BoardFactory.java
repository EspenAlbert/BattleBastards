package com.tdt4240.RawHeroes.createGame.factory;

import com.tdt4240.RawHeroes.createGame.boards.StandardBoard;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

/**
 * Created by espen1 on 07.03.2015.
 */
public class BoardFactory implements IBoardFactory{

    private static BoardFactory instance;

    public static BoardFactory getInstance() {
        if(instance == null) {
            instance = new BoardFactory();
        }
        return instance;
    }

    public static final String STANDARD = "STANDARD-BOARD";

    @Override
    public IBoard getBoard(String boardName) {
        if(boardName.equals(STANDARD)) {
            return new StandardBoard();
        } else {
            return null;
        }
    }
}

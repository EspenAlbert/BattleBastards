package com.tdt4240.RawHeroes.createGame.boards;

import com.tdt4240.RawHeroes.createGame.factory.IBoardFactory;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

/**
 * Created by espen1 on 07.03.2015.
 */
public class BoardFactory implements IBoardFactory {

    private static BoardFactory instance;

    public static BoardFactory getInstance() {
        if(instance == null) {
            instance = new BoardFactory();
        }
        return instance;
    }


    @Override
    public IBoard getBoard(BoardType boardName) {

        switch (boardName) {
            case STANDARD_BOARD:
                return new StandardBoard();
        }
        return null;
    }
}

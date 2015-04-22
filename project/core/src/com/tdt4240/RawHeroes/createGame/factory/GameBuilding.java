package com.tdt4240.RawHeroes.createGame.factory;

import com.tdt4240.RawHeroes.createGame.boards.BoardFactory;
import com.tdt4240.RawHeroes.createGame.boards.BoardType;
import com.tdt4240.RawHeroes.createGame.games.EliminateEnemyUnitsFactory;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Games;

/**
 * Created by espen1 on 07.03.2015.
 */
public class GameBuilding implements IGameBuilding {

    private static GameBuilding instance;
    private final IGameFactory eliminateEnemyUnitsFactory;
    private final BoardFactory boardFactory;

    public static GameBuilding getInstance() {
        if(instance == null) {
            instance = new GameBuilding();
        }
        return instance;
    }

    private GameBuilding() {
        eliminateEnemyUnitsFactory = EliminateEnemyUnitsFactory.getInstance();
        boardFactory = BoardFactory.getInstance();
    }


    //Standard board
    @Override
    public Game createGame(Games gameType, String player1, String player2) {
        IBoard board = boardFactory.getBoard(BoardType.STANDARD_BOARD);
        Game game = getGame(gameType, player1, player2);
        game.setBoard(board);
        return game;
    }

    private Game getGame(Games gameType, String player1, String player2) {
        switch (gameType) {
            case KILL_ALL_ENEMY_UNITS:
                return eliminateEnemyUnitsFactory.createGame(player1, player2);
            case CAPTURE_THE_FLAG:
                break;
        }
        return null;
    }

    //Customized board
    @Override
    public Game createGame(Games gameType, String player1, String player2, BoardType boardType) {
        Game game = getGame(gameType, player1, player2);
        game.setBoard(boardFactory.getBoard(boardType));
        return game;
    }
}

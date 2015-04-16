package com.tdt4240.RawHeroes.createGame.factory;

import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Games;

/**
 * Created by espen1 on 07.03.2015.
 */
public class GameBuilding implements IGameBuilding {

    private static GameBuilding instance;
    private final EliminateEnemyUnitsFactory eliminateEnemyUnitsFactory;
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


    @Override
    public Game createGame(Games gameType, String player1, String player2) {
        IBoard board = boardFactory.getBoard(boardFactory.STANDARD);
        Game game = null;
        switch (gameType) {
            case KILL_ALL_ENEMY_UNITS:
                game = eliminateEnemyUnitsFactory.createGame(player1, player2);
                break;
            case CAPTURE_THE_FLAG:
                break;
            case KILL_ALL_ENEMY_UNITS_WITH_MOVES:
                game = eliminateEnemyUnitsFactory.createGame(player1, player2);
                game.setLastMoves(eliminateEnemyUnitsFactory.createMoves(eliminateEnemyUnitsFactory.TYPE2, board));
                break;
        }
        game.setBoard(board);
        return game;
    }
}

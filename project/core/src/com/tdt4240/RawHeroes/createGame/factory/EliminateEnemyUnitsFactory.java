package com.tdt4240.RawHeroes.createGame.factory;

import com.tdt4240.RawHeroes.createGame.games.EliminateAllEnemiesGame;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;

/**
 * Created by espen1 on 07.03.2015.
 */
public class EliminateEnemyUnitsFactory {
    private static EliminateEnemyUnitsFactory instance;

    public static EliminateEnemyUnitsFactory getInstance() {
        if(instance == null) {
            instance = new EliminateEnemyUnitsFactory();
        }
        return instance;
    }

    public Game createGame(String player1, String player2) {
        return new EliminateAllEnemiesGame(player1, player2);
    }
}

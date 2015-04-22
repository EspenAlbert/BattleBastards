package com.tdt4240.RawHeroes.createGame.games;

import com.tdt4240.RawHeroes.createGame.factory.IGameFactory;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;

/**
 * Created by espen1 on 07.03.2015.
 */
public class EliminateEnemyUnitsFactory implements IGameFactory {
    private static EliminateEnemyUnitsFactory instance;

    public static EliminateEnemyUnitsFactory getInstance() {
        if(instance == null) {
            instance = new EliminateEnemyUnitsFactory();
        }
        return instance;
    }


    @Override
    public Game createGame(String player1, String player2) {
        return new EliminateAllEnemiesGame(player1, player2);
    }
}

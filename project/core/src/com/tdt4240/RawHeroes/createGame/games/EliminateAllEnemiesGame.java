package com.tdt4240.RawHeroes.createGame.games;

import com.tdt4240.RawHeroes.createGame.winConditions.NoEnemyUnitLeftWinCondition;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;

/**
 * Created by espen1 on 07.03.2015.
 */
public class EliminateAllEnemiesGame extends Game {


    public EliminateAllEnemiesGame(String player1Nickname, String player2Nickname) {
        super(player1Nickname, player2Nickname, new NoEnemyUnitLeftWinCondition());
    }
}

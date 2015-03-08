package com.tdt4240.RawHeroes.createGame.factory;

import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;

/**
 * Created by espen1 on 08.03.2015.
 */
public interface IGameFactory {
    Game createGame(IBoard board, String player1, String player2);
}

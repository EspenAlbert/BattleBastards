package com.tdt4240.RawHeroes.createGame.factory;

import com.tdt4240.RawHeroes.createGame.boards.BoardType;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Games;

/**
 * Created by espen1 on 07.03.2015.
 */
public interface IGameBuilding {

    public Game createGame(Games game, String player1, String player2);
    public Game createGame(Games game, String player1, String player2, BoardType boardType);
}

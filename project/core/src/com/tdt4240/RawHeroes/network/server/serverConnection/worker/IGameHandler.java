package com.tdt4240.RawHeroes.network.server.serverConnection.worker;

import com.tdt4240.RawHeroes.network.server.serverConnection.player.Player;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Games;

import java.sql.SQLException;

/**
 * Created by espen1 on 05.03.2015.
 */
public interface IGameHandler {
    int createGame(String player1, String player2, Games gameType) throws Exception;
    int findGame(String player1, String player2) throws SQLException;
    int findGame(int gameId);
    boolean checkPlayer(Player player) throws Exception;
}

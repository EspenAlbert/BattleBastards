package com.tdt4240.RawHeroes.network.server.serverConnection.worker;

import com.tdt4240.RawHeroes.event.move.Move;
import com.tdt4240.RawHeroes.network.server.serverConnection.player.Player;
import com.tdt4240.RawHeroes.network.server.serverConnection.worker.exceptions.GameNotFoundException;
import com.tdt4240.RawHeroes.network.server.serverConnection.worker.exceptions.NotYourGameException;
import com.tdt4240.RawHeroes.network.server.serverConnection.worker.exceptions.NotYourTurnException;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Games;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by espen1 on 05.03.2015.
 */
public interface IGameHandler {
    int createGame(String player1, String player2, Games gameType) throws Exception;
    int findGame(String player1, String player2) throws SQLException;
    Game getGame(String username, int gameId) throws GameNotFoundException, NotYourGameException;

    boolean checkPlayer(Player player) throws Exception;

    boolean isActiveGame(int gameId) throws SQLException;

    void doMoves(int gameId, String username, ArrayList<Move> moves) throws NotYourGameException, GameNotFoundException, NotYourTurnException, Exception;

    ArrayList<Integer> getGameIds(String username) throws SQLException;
}

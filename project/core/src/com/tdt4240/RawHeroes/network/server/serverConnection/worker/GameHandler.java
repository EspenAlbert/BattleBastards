package com.tdt4240.RawHeroes.network.server.serverConnection.worker;

import com.tdt4240.RawHeroes.createGame.factory.GameBuilding;
import com.tdt4240.RawHeroes.network.server.database.DatabaseConnector;
import com.tdt4240.RawHeroes.network.server.serverConnection.player.Player;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Games;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by espen1 on 05.03.2015.
 */
public class GameHandler implements IGameHandler{


    private final DatabaseConnector databaseConnector;
    private static GameHandler instance;

    private GameHandler() throws Exception {
        databaseConnector = DatabaseConnector.getInstance();
    }

    public static GameHandler getInstance() throws Exception {
        if(instance == null) {
            instance = new GameHandler();
        }
        return instance;
    }

    @Override
    public int createGame(String player1, String player2, Games gameType) throws Exception {
        Game game = GameBuilding.getInstance().createGame(gameType, player1, player2);
        return databaseConnector.insertGame(game);
    }

    @Override
    public int findGame(String player1, String player2) throws SQLException {
        return databaseConnector.findGame(player1, player2);
    }

    @Override
    public int findGame(int gameId) {
        return 0;
    }

    @Override
    public boolean checkPlayer(Player player) throws Exception {
        Player storedPlayer = (Player) databaseConnector.getJavaObject("players", "username", player.getUsername());
        return player.getPassword().equals(storedPlayer.getPassword());

    }
}

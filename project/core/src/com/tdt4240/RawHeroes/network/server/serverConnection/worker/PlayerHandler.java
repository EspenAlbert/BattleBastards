package com.tdt4240.RawHeroes.network.server.serverConnection.worker;

import com.tdt4240.RawHeroes.network.server.database.DatabaseConnection;
import com.tdt4240.RawHeroes.network.server.database.DatabaseConnector;
import com.tdt4240.RawHeroes.network.server.serverConnection.player.Player;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by espen1 on 05.03.2015.
 */
public class PlayerHandler implements IPlayerHandler{


    private final DatabaseConnector databaseConnector;
    private static PlayerHandler instance;

    private PlayerHandler() throws Exception {
        databaseConnector = DatabaseConnector.getInstance();
    }

    public static PlayerHandler getInstance() throws Exception {
        if(instance == null) {
            instance = new PlayerHandler();
        }
        return instance;
    }

    @Override
    public boolean usernameIsAvailable(String username) throws SQLException {
        return databaseConnector.usernameAvailable(username);
    }

    @Override
    public boolean createPlayer(Player player) throws IOException, SQLException {
        HashMap<String, Object> columns = new HashMap<String, Object>();
        columns.put("javaObject", player);
        databaseConnector.insertRow("players", columns, "username", player.getUsername());
        return true;
    }

    @Override
    public boolean checkPlayer(Player player) throws Exception {
        Player storedPlayer = (Player) databaseConnector.getJavaObject("players", "username", player.getUsername());
        return player.getPassword().equals(storedPlayer.getPassword());

    }
}

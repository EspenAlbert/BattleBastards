package com.tdt4240.RawHeroes.network.server.serverConnection.worker;

import com.badlogic.gdx.utils.Array;
import com.tdt4240.RawHeroes.network.server.database.DatabaseConnector;
import com.tdt4240.RawHeroes.network.server.serverConnection.player.Player;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public Player getPlayer(String username) throws Exception {
        return (Player)databaseConnector.getJavaObject("players", "username", username, -1);
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
        Player storedPlayer = (Player) databaseConnector.getJavaObject("players", "username", player.getUsername(), -1);
        return player.getPassword().equals(storedPlayer.getPassword());
    }
    public boolean changePassword(Player player) throws Exception{
        Player storedPlayer = (Player) databaseConnector.getJavaObject("players", "username", player.getUsername(), -1);
        storedPlayer.setPassword(player.getPassword());
        HashMap<String, Object> columns = new HashMap<String, Object>();
        columns.put("javaObject", storedPlayer);
        databaseConnector.updatePlayer(columns, storedPlayer.getUsername());
        return true;
    }
    public ArrayList<Player> getFriendList(String username) throws Exception {
        Player storedPlayer = (Player) databaseConnector.getJavaObject("players", "username", username, -1);
        return storedPlayer.getFriendList();
    }

    public boolean addFriend(Player player, Player playerFriend) throws Exception {
        Player storedPlayer = (Player) databaseConnector.getJavaObject("players", "username", player.getUsername(), -1);
        boolean addedToList = storedPlayer.addToFriendList(playerFriend);
        if(addedToList) {
            HashMap<String, Object> columns = new HashMap<String, Object>();
            columns.put("javaObject", storedPlayer);
            databaseConnector.updatePlayer(columns, storedPlayer.getUsername());
            return true;
        }
        else
            return false;

    }
}

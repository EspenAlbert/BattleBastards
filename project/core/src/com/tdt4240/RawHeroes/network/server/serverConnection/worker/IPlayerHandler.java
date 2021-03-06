package com.tdt4240.RawHeroes.network.server.serverConnection.worker;

import com.tdt4240.RawHeroes.network.server.serverConnection.player.Player;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by espen1 on 05.03.2015.
 */
public interface IPlayerHandler {
    boolean usernameIsAvailable(String username) throws SQLException;
    boolean createPlayer(Player player) throws IOException, SQLException;
    boolean checkPlayer(Player player) throws Exception;
    boolean addFriend(Player player, Player playerFriend) throws Exception;
    ArrayList<Player> getFriendList(String username) throws Exception;
    boolean changePassword(Player player) throws Exception;
}

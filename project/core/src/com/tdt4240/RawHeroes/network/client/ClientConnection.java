package com.tdt4240.RawHeroes.network.client;

import com.tdt4240.RawHeroes.logic.move.Move;
import com.tdt4240.RawHeroes.network.message.Message;

/**
 * Created by espen1 on 27.02.2015.
 */
public class ClientConnection implements IClientConnection {
    private static ClientConnection ourInstance = new ClientConnection();
    private String username;
    private String password;

    public static ClientConnection getInstance() {
        return ourInstance;
    }

    private ClientConnection() {
    }

    @Override
    public int[] getMyGames() {
        return new int[0];
    }

    @Override
    public int createNewGame(String opponent) {
        return 0;
    }

    @Override
    public Message doMove(int id, Move[] moves) {
        return null;
    }

    @Override
    public Message login(String userName, String password) {
        return null;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Message getGame(int gameId) {
        return null;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

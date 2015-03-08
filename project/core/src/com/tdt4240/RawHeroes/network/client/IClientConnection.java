package com.tdt4240.RawHeroes.network.client;

import com.tdt4240.RawHeroes.event.move.Move;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseMessage;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Games;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface IClientConnection {
    int[] getMyGames();
    ResponseMessage createNewGame(String opponent, Games gameType);
    ResponseMessage doMove(int id, Move[] moves);
    ResponseMessage login(String userName, String password);
    void setPassword(String password);
    void setUsername(String username);
    String getUsername();

    ResponseMessage getGame(int gameId);
}

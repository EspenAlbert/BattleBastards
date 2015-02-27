package com.tdt4240.RawHeroes.network.client;

import com.tdt4240.RawHeroes.logic.move.Move;
import com.tdt4240.RawHeroes.network.message.Message;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface IClientConnection {
    int[] getMyGames(String nickName);
    int createNewGame(String opponent);
    Message doMove(int id, Move[] moves);

}

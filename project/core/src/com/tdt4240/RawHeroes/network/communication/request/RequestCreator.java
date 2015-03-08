package com.tdt4240.RawHeroes.network.communication.request;

import com.tdt4240.RawHeroes.network.server.serverConnection.player.Player;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Games;

import org.json.simple.JSONObject;

/**
 * Created by espen1 on 05.03.2015.
 */
public class RequestCreator {
    public static JSONObject getCreateUserRequest(String username, String password) {
        JSONObject json = new JSONObject();
        json.put("request",new RequestMessage(RequestTypes.CREATE_USER, new Player(username, password)));
        return json;
    }
    public static JSONObject getLoginRequest(String username, String password) {
        JSONObject json = new JSONObject();
        json.put("request",new RequestMessage(RequestTypes.LOGIN, new Player(username, password)));
        return json;
    }
    public static JSONObject getCreateGameRequest(String username, String password, String challenger, Games gameType) {
        JSONObject json = new JSONObject();
        RequestMessage message = new RequestMessage(RequestTypes.CREATE_GAME, new Player(username, password));
        message.addParameter(challenger);
        message.addParameter(gameType);
        json.put("request", message);
        return json;
    }
}

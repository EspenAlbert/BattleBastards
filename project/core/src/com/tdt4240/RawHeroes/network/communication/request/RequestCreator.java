package com.tdt4240.RawHeroes.network.communication.request;

import com.tdt4240.RawHeroes.event.move.Move;
import com.tdt4240.RawHeroes.network.server.serverConnection.player.Player;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Games;

import org.json.simple.JSONObject;

import java.util.ArrayList;

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

    public static JSONObject getDoMoveRequest(String username, String password, int id, ArrayList<Move> moves) {
        JSONObject json = new JSONObject();
        RequestMessage message = new RequestMessage(RequestTypes.DO_MOVES, new Player(username, password));
        message.addParameter(id);
        message.addParameter(moves);
        json.put("request", message);
        return json;
    }

    public static JSONObject getGetGameRequest(String username, String password, int gameId) {
        JSONObject json = new JSONObject();
        RequestMessage message = new RequestMessage(RequestTypes.GET_GAME, new Player(username, password));
        message.addParameter(gameId);
        json.put("request", message);
        return json;
    }

    public static JSONObject getGameIds(String username, String password) {
        JSONObject json = new JSONObject();
        RequestMessage message = new RequestMessage(RequestTypes.GET_GAMEIDS, new Player(username, password));
        message.addParameter(username);
        json.put("request", message);
        return json;
    }
    public static JSONObject getDeleteGame(String username, String password, int gameId){
        JSONObject json = new JSONObject();
        RequestMessage message = new RequestMessage(RequestTypes.DELETE_GAME, new Player(username, password));
        message.addParameter(gameId);
        json.put("request", message);
        return json;
    }

    public static JSONObject getChangePassword(String username, String password, String newPassword) {
        JSONObject json = new JSONObject();
        RequestMessage message = new RequestMessage(RequestTypes.CHANGE_PASSWORD, new Player(username, password));
        message.addParameter(newPassword);
        json.put("request", message);
        return json;
    }

    public static JSONObject getAddedToFriendListRequest(String username, String password, String username1) {
        JSONObject json = new JSONObject();
        RequestMessage message = new RequestMessage(RequestTypes.ADD_TO_FRIENDLIST, new Player(username, password));
        message.addParameter(username1);
        json.put("request", message);
        return json;
    }

    public static JSONObject getGetFriendList(String username, String password) {
        JSONObject json = new JSONObject();
        RequestMessage message = new RequestMessage(RequestTypes.GET_FRIENDLIST, new Player(username, password));
        message.addParameter(username);
        json.put("request", message);
        return json;
    }

    public static JSONObject getIsPlaying(String username, String password, String opponent) {
        JSONObject json = new JSONObject();
        RequestMessage message = new RequestMessage(RequestTypes.IS_PLAYING, new Player(username, password));
        message.addParameter(opponent);
        json.put("request", message);
        return json;
    }
}

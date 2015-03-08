package com.tdt4240.RawHeroes.network.server.serverConnection.worker;

import com.tdt4240.RawHeroes.network.communication.Response.ResponseCreator;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseMessage;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseType;
import com.tdt4240.RawHeroes.network.communication.request.RequestMessage;
import com.tdt4240.RawHeroes.network.communication.request.RequestTypes;
import com.tdt4240.RawHeroes.network.server.serverConnection.player.Player;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Games;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by espen on 13.03.14.
 */
public class Worker extends Thread {

    private String username;
    private Socket connection;
    private ObjectOutputStream toClient;
    private ObjectInputStream fromClient;

    public Worker(Socket connection) {
        this.connection = connection;
        System.out.println("worker created");
        this.start();
    }

    public void run() {
        System.out.println("workerStarted");
            try {
                toClient = new ObjectOutputStream(connection.getOutputStream());
                fromClient = new ObjectInputStream(connection.getInputStream());
                JSONObject obj = (JSONObject)fromClient.readObject();
                decodeMessage(obj);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        System.out.println("worker dying..");
    }

    public void sendJSON(JSONObject json) {
        try {
            System.out.println("Sending message"+ json);
            toClient.writeObject(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void decodeMessage(JSONObject obj) {
        System.out.println(obj);
        JSONObject response = new JSONObject();
        RequestMessage request = null;
        try {
            request = (RequestMessage) obj.get("request");
        } catch (ClassCastException exception) {
            //TODO: Return a invalid request message
        }
        RequestTypes requestType = request.getType();
        try {
            switch (requestType) {
                case CREATE_USER:
                    response.put("response", createUser(request));
                    break;
                case LOGIN:
                    response.put("response", login(request));
                    break;
                case CREATE_GAME:
                    response.put("response", createGame(request));
                    break;
            }
        }catch(Exception exception) {
            exception.printStackTrace();
            response.put("response", new ResponseMessage(ResponseType.FAILURE, "There was an exception on the server side"));
        }
        sendJSON(response);
    }

    private ResponseMessage createGame(RequestMessage request) throws Exception {
        //Check both players
        String hostUsername = request.getPlayer().getUsername();
        ResponseMessage loginResponse = login(new RequestMessage(RequestTypes.LOGIN, request.getPlayer()));
        if(loginResponse.getType() == ResponseType.FAILURE) return loginResponse;

        String challengedPlayer = (String) request.getParameters().get(0);
        PlayerHandler playerHandler = PlayerHandler.getInstance();
        boolean challengedPlayerIsReal = !playerHandler.usernameIsAvailable(challengedPlayer);
        if(!challengedPlayerIsReal) return ResponseCreator.getChallengePlayerDoesNotExist();
        //CreateGame
        //Check that there is not an existing game
        GameHandler gameHandler = GameHandler.getInstance();
        int id = gameHandler.findGame(hostUsername, challengedPlayer);
        if(id > -1) return ResponseCreator.getGameAlreadyExist(challengedPlayer);
        //Create a new game
        Games gameType = (Games) request.getParameters().get(1);
        int gameId = gameHandler.createGame(hostUsername, challengedPlayer, gameType);
        if(gameId > 0) return ResponseCreator.getCreateGameSuccess(gameId);
        return ResponseCreator.getFailedToCreateGame();
    }

    private ResponseMessage login(RequestMessage request) throws Exception {
        PlayerHandler playerHandler = PlayerHandler.getInstance();
        Player user = request.getPlayer();
        try {
            boolean loginSuccess = playerHandler.checkPlayer(user);
            if(loginSuccess) return ResponseCreator.getLoginSuccess();
            else return ResponseCreator.getWrongUsernamePassword();
        } catch (Exception exception) {
            return ResponseCreator.getWrongUsernamePassword();
        }

    }

    private ResponseMessage createUser(RequestMessage request) throws Exception {
        PlayerHandler playerHandler = PlayerHandler.getInstance();
        Player newUser = request.getPlayer();
        boolean available = playerHandler.usernameIsAvailable(newUser.getUsername());
        if(!available) return ResponseCreator.getUsernameNotAvailable();
        boolean createdSuccessfully = playerHandler.createPlayer(newUser);
        if(createdSuccessfully) return ResponseCreator.getCreateUserSuccess();
        return ResponseCreator.getFailedToCreateUser();
    }


    public void disconnect() {
        if(toClient != null) {
            try {
                toClient.close();
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

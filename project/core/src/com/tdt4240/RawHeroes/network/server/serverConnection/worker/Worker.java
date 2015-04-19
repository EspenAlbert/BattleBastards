package com.tdt4240.RawHeroes.network.server.serverConnection.worker;

import com.tdt4240.RawHeroes.event.move.Move;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseCreator;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseMessage;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseType;
import com.tdt4240.RawHeroes.network.communication.request.RequestMessage;
import com.tdt4240.RawHeroes.network.communication.request.RequestTypes;
import com.tdt4240.RawHeroes.network.server.serverConnection.player.Player;
import com.tdt4240.RawHeroes.network.server.serverConnection.worker.exceptions.GameNotFoundException;
import com.tdt4240.RawHeroes.network.server.serverConnection.worker.exceptions.NotYourGameException;
import com.tdt4240.RawHeroes.network.server.serverConnection.worker.exceptions.NotYourTurnException;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Games;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
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
            disconnect();
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
            response.put("response", new ResponseMessage(ResponseType.FAILURE, "You requested an invalid request"));
        }
        RequestTypes requestType = request.getType();
        try {
            boolean loginFirst = true;
            //Login no matter what, woops, not if you want to create a new user...
            if(requestType == RequestTypes.CREATE_USER) {
                loginFirst = false;
            }
            ResponseMessage loginResponse = null;
            if(loginFirst) {
                loginResponse = login(new RequestMessage(RequestTypes.LOGIN, request.getPlayer()));
                if(loginResponse.getType() == ResponseType.FAILURE) {
                    response.put("response", loginResponse);
                    sendJSON(response);
                    return;
                }
            }
            switch (requestType) {
                case CREATE_USER:
                    response.put("response", createUser(request));
                    break;
                case LOGIN:
                    response.put("response", loginResponse);
                    break;
                case CREATE_GAME:
                    response.put("response", createGame(request));
                    break;
                case DO_MOVES:
                    response.put("response", doMoves(request));
                    break;
                case GET_GAME:
                    response.put("response", getGame(request));
                    break;
                case GET_GAMEIDS:
                    response.put("response", getGameIds(request));
                    break;
                case DELETE_GAME:
                    response.put("response", deleteGame(request));
                    break;
            }
        }catch(Exception exception) {
            exception.printStackTrace();
            response.put("response", new ResponseMessage(ResponseType.FAILURE, "There was an exception on the server side"));
        }
        sendJSON(response);
    }

    private ResponseMessage deleteGame(RequestMessage request)throws Exception{
        GameHandler gameHandler = GameHandler.getInstance();
        System.out.println("I WAS HERE");
        Integer gameId = (Integer) request.getParameters().get(0);
        try {
            gameHandler.deleteGame(gameId);
        } catch (GameNotFoundException e) {
            return ResponseCreator.getInvalidGameException(gameId);
        } catch (NotYourGameException e) {
            return ResponseCreator.getNotYourGameException(gameId);
        }
        return ResponseCreator.getDeletedGame();
    }

    private ResponseMessage getGameIds(RequestMessage request) throws Exception {
        GameHandler gameHandler = GameHandler.getInstance();
        String username = (String) request.getParameters().get(0);
        ArrayList<Integer> gameIds = gameHandler.getGameIds(username);
        return ResponseCreator.getGameIds(gameIds);



    }

    private ResponseMessage getGame(RequestMessage request) throws Exception {
        GameHandler gameHandler = GameHandler.getInstance();
        Integer gameId = (Integer) request.getParameters().get(0);
        try {
            Game game = gameHandler.getGame(request.getPlayer().getUsername(), gameId);
            return ResponseCreator.getGameSuccess(game);
        } catch (GameNotFoundException e) {
            return ResponseCreator.getInvalidGameException(gameId);
        } catch (NotYourGameException e) {
            return ResponseCreator.getNotYourGameException(gameId);
        }
    }

    private ResponseMessage doMoves(RequestMessage request) throws Exception {
        GameHandler gameHandler = GameHandler.getInstance();
        Integer gameId = (Integer) request.getParameters().get(0);
        ArrayList<Move> moves  = (ArrayList<Move>) request.getParameters().get(1);
        try {
            gameHandler.doMoves(gameId, request.getPlayer().getUsername(), moves);
            return ResponseCreator.getDoMovesSuccess(gameId);
        }catch (GameNotFoundException exception) {
            return ResponseCreator.getInvalidGameException(gameId);
        } catch (NotYourGameException e) {
            return ResponseCreator.getNotYourGameException(gameId);
        } catch (NotYourTurnException e) {
            return ResponseCreator.getNotYourTurnException(gameId);
        }
    }

    private ResponseMessage createGame(RequestMessage request) throws Exception {
        String hostUsername = request.getPlayer().getUsername();
        //Check challenged player

        String challengedPlayer = (String) request.getParameters().get(0);
        PlayerHandler playerHandler = PlayerHandler.getInstance();
        boolean challengedPlayerIsReal = !playerHandler.usernameIsAvailable(challengedPlayer);
        if(!challengedPlayerIsReal) return ResponseCreator.getChallengePlayerDoesNotExist();
        //CreateGame
        //Check that there is not an existing launcher
        GameHandler gameHandler = GameHandler.getInstance();
        int id = gameHandler.findGame(hostUsername, challengedPlayer);
        if(id > -1) return ResponseCreator.getGameAlreadyExist(challengedPlayer);
        //Create a new launcher
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

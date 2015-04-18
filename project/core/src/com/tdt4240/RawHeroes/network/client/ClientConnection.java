package com.tdt4240.RawHeroes.network.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.tdt4240.RawHeroes.event.move.Move;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseMessage;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseType;
import com.tdt4240.RawHeroes.network.communication.request.RequestCreator;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Games;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public class ClientConnection implements IClientConnection {
    private static ClientConnection ourInstance;

    private final String serverAddress;
    private final int serverPort;

    private ObjectInputStream in;
    private ObjectOutputStream toServer;
    private Socket clientSocket;

    private String username;
    private String password;



    public static ClientConnection getInstance() {
        if (ourInstance == null) {
            ourInstance = new ClientConnection(GameConstants.SERVER_IP, GameConstants.SERVER_PORT);
        }
        return ourInstance;
    }

    public static void main(String[] args) {
        System.out.println("Starting server");
        ClientConnection connection = ClientConnection.getInstance();

    }
    private ClientConnection(String address, int port) {
        this.serverAddress = address;
        this.serverPort = port;
        clientSocket = null;

        //ResponseMessage responseMessage= createUser("Espen4", "1234");
        ResponseMessage responseMessage = sendRequestAndWaitForResponse(RequestCreator.getLoginRequest("Espen4", "1234"));

        //String[] challengers = {"Espen", "Espen2", "Espen3", "XXXX"};
        username = "Espen4";
        password = "1234";
        System.out.println("Got response: " + responseMessage.getType() + responseMessage.getContent());
        /*
        ResponseMessage createGameResponse = createNewGame("Espen3", Games.KILL_ALL_ENEMY_UNITS);
        ResponseMessage responseMessage = getGame((Integer) createGameResponse.getContent());
        System.out.println("Got response: " + responseMessage.getType() + responseMessage.getContent());
        ResponseMessage responseMessageDoMoves = doMoves((Integer) createGameResponse.getContent(), null);
        System.out.println("Got response: " + responseMessageDoMoves.getType() + responseMessageDoMoves.getContent());

*/


//        for (String challenger : challengers) {
 //           ResponseMessage responseMessage = createNewGame(challenger, Games.KILL_ALL_ENEMY_UNITS);
 //          System.out.println("Got response: " + responseMessage.getType() + responseMessage.getContent());
  //      }
        System.out.println("Done creating client connection");
    }

    public ResponseMessage sendRequestAndWaitForResponse(JSONObject json) {
        try {
            SocketHints socketHints = new SocketHints();
            socketHints.connectTimeout = 4000;
            clientSocket = Gdx.net.newClientSocket(Net.Protocol.TCP, serverAddress, serverPort, socketHints);
            toServer = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("about to send" + json);
            toServer.writeObject(json);
            JSONObject obj = (JSONObject) in.readObject();
            return (ResponseMessage) obj.get("response");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ResponseMessage(ResponseType.FAILURE, "Didn't get a response from server");
    }


    public ResponseMessage createUser(String username, String password) {
        return sendRequestAndWaitForResponse(RequestCreator.getCreateUserRequest(username, password));
    }

    @Override
    public int[] getMyGames() {
        return new int[0];
    }

    @Override
    public ResponseMessage createNewGame(String opponent, Games gameType) {
        return sendRequestAndWaitForResponse(RequestCreator.getCreateGameRequest(username, password, opponent, gameType));
    }

    @Override
    public ResponseMessage doMoves(int id, ArrayList<Move> moves) {
        return sendRequestAndWaitForResponse(RequestCreator.getDoMoveRequest(username, password, id,moves));
    }

    @Override
    public ResponseMessage login(String userName, String password) {
        return sendRequestAndWaitForResponse(RequestCreator.getLoginRequest(userName, password));
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public ResponseMessage getGame(int gameId) {
        return sendRequestAndWaitForResponse(RequestCreator.getGetGameRequest(username, password, gameId));
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

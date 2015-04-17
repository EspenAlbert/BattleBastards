package com.tdt4240.RawHeroes.network.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseMessage;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseType;
import com.tdt4240.RawHeroes.network.communication.request.RequestCreator;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import static com.badlogic.gdx.Net.Protocol.TCP;

/**
 * Created by espen1 on 16.04.2015.
 */
public class ClientConnectionAndroid {

    private Socket connection;
    private ObjectOutputStream toServer;
    private ObjectInputStream in;

    public ClientConnectionAndroid() {
        /*
        List<String> addresses = new ArrayList<String>();

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            for(NetworkInterface ni : Collections.list(interfaces)){
                for(InetAddress address : Collections.list(ni.getInetAddresses()))
                {
                    if(address instanceof Inet4Address){
                        addresses.add(address.getHostAddress());
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
*/
        SocketHints socketHints = new SocketHints();
        socketHints.connectTimeout = 4000;
        connection = Gdx.net.newClientSocket(Net.Protocol.TCP, GameConstants.SERVER_IP, GameConstants.SERVER_PORT, socketHints);
        ResponseMessage responseMessage = sendRequestAndWaitForResponse(RequestCreator.getLoginRequest("Espen4", "1234"));
        System.out.println("Got response: " + responseMessage.getType() + responseMessage.getContent());



    }
    public ResponseMessage sendRequestAndWaitForResponse(JSONObject json) {
        try {
            toServer = new ObjectOutputStream(connection.getOutputStream());
            in = new ObjectInputStream(connection.getInputStream());
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
}

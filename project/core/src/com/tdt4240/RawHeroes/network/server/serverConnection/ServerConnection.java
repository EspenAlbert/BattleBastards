package com.tdt4240.RawHeroes.network.server.serverConnection;
import com.tdt4240.RawHeroes.network.server.serverConnection.worker.Worker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by espen1 on 03.03.2015.
 */
public class ServerConnection {

    private boolean running;
    private ObjectInputStream in;
    private ObjectOutputStream toClient;

    public ServerConnection(int port) {
        ServerSocket welcomeSocket = null;
        try {
            welcomeSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server up and running");
        running = true;

        while(running)
        {

            Socket connectionSocket = null;
            try {
                connectionSocket = welcomeSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (connectionSocket != null)
            {
                try {
                    new Worker(connectionSocket);
                    System.out.println("new connection");
                }catch(NullPointerException exception) {
                    System.out.println("null pointer...");
                    exception.printStackTrace();
                }
                // new LoginHandler(connectionSocket, this);
            }
        }
        System.out.println("Server shutting down");
    }
    public static void main(String args[]) {
        System.out.println("Starting server");
        ServerConnection server = new ServerConnection(7777);
        //CalendarProperties properties = CalendarProperties.getInstance();
        //Server server = new Server(properties.getListenport());
    }


}
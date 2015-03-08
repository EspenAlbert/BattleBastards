package com.tdt4240.RawHeroes.network.server.database;

import com.tdt4240.RawHeroes.network.server.serverConnection.player.Child;
import com.tdt4240.RawHeroes.network.server.serverConnection.player.Player;
import com.tdt4240.RawHeroes.network.server.serverConnection.player.PlayerTypes;

/**
 * Created by espen1 on 04.03.2015.
 */
public class DatabaseTester {
    public static void main(String[] args) {
        System.out.println("In driver....");
        try {
            SaveObject objectSaver = new SaveObject();
            Player player = new Player("Espen", "boss");
            player.setPlayerType(PlayerTypes.MASTER);
            player.setChild(new Child("Lovely Anna"));
            objectSaver.setJavaObject(player);
            objectSaver.saveObject();
            Player received = (Player) objectSaver.getObject();
            System.out.println("Success!!");
            //System.out.println("Got back: " + received.getUsername() + " with pwd: " + received.getPassword() + " and user type: " + received.getPlayerType() + " with child: " + received.getChild().getName());

            System.out.println("Checking: Expecting  player = received: " + player.equals(received));

            ///Testing update:
            Player player2 = new Player("Albert", "1234");
            objectSaver.setJavaObject(player2);
            objectSaver.updateObject();

            Player receivedPlayer2 = (Player) objectSaver.getJavaObject();
            System.out.println("\n\nGot back changed: " + receivedPlayer2.getUsername() + " with pwd: " + receivedPlayer2.getPassword());
            Player player3 = new Player("Player3", "1234");
            objectSaver.setJavaObject(player3);
            objectSaver.updateObject();
            Player receivedPlayer3 = (Player) objectSaver.getJavaObject();
            System.out.println("\nGot back with another change: " + receivedPlayer3.getUsername() + " with pwd: " + receivedPlayer3.getPassword());



        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}

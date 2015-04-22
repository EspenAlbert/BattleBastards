package com.tdt4240.RawHeroes.network.server.database;

import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by espen1 on 05.03.2015.
 */
public interface IDatabaseConnector {

    boolean usernameAvailable(String username) throws SQLException;
    int findGame(String player1, String player2) throws SQLException;
    void insertRow(String table, HashMap<String, Object> javaObjectColumns, String primaryKeyCol, String primaryKeyValue) throws Exception;
    void updateJavaObject(String table, String primaryKey, int primaryKeyValue, Object javaObject) throws Exception;
    Object getJavaObject(String table, String primaryKey, String primaryKeyValue, int primaryKeyValueInt) throws Exception;

    Game getGame(int gameId) throws Exception;
    int insertGame(Game game) throws Exception;

    boolean isActiveGame(int gameId) throws SQLException;

    void updateGame(Game game) throws Exception;

    void updateJavaObject(String table, String primaryKey, String primaryKeyValue, Object javaObject) throws Exception;
    void updatePlayer(HashMap<String, Object> javaObjectColumns, String primaryKeyValue) throws SQLException, IOException;
    ArrayList<Integer> getAllKeys(String username) throws SQLException;
    int deleteGame(int gameId) throws SQLException;
}

package com.tdt4240.RawHeroes.network.server.database;

import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by espen1 on 05.03.2015.
 */
public class DatabaseConnector implements IDatabaseConnector {
    public static final String TABLE_PLAYERS = "players";
    public static final String PLAYERS_PRIMARY_KEY = "username";
    public static final String TABLE_GAMES = "games";
    public static final String GAMES_PRIMARY_KEY = "gameId";
    private static DatabaseConnector instance;
    private final Connection myConnection;

    public DatabaseConnector() throws Exception {
        myConnection = DatabaseConnection.getConnection();
    }

    public static DatabaseConnector getInstance() throws Exception {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }

    @Override
    public boolean usernameAvailable(String username) throws SQLException {
        return !rowExist(TABLE_PLAYERS, PLAYERS_PRIMARY_KEY, username, -1);
    }
    @Override
    public boolean isActiveGame(int gameId) throws SQLException {
        return rowExist(TABLE_GAMES, GAMES_PRIMARY_KEY, "", gameId);
    }

    @Override
    public void updateGame(Game game) throws Exception {
        updateJavaObject(TABLE_GAMES, GAMES_PRIMARY_KEY, game.getId(), game);
    }

    private boolean rowExist(String table, String primaryKey, String primaryKeyValue, int primaryKeyValueInt) throws SQLException {
        PreparedStatement ps = null;
        String sql = null;
        sql = "select * from " + table + " where " + primaryKey + "= ?";
        ps = myConnection.prepareStatement(sql);
        if(primaryKeyValueInt > 0) ps.setInt(1,primaryKeyValueInt);
        else ps.setString(1, primaryKeyValue);
        ResultSet result = ps.executeQuery();
        return result.next();
    }

    @Override
    public int findGame(String player1, String player2) throws SQLException {
        PreparedStatement ps = null;
        String sql = null;
        sql = "select * from games where (player1 = ? or player1 = ? ) and (player2 = ? or player2 = ? );";

        ps = myConnection.prepareStatement(sql);
        ps.setString(1, player1);
        ps.setString(2, player2);
        ps.setString(3, player1);
        ps.setString(4, player2);
        ResultSet result = ps.executeQuery();
        if (!result.next()) return -1;
        return result.getInt("gameId");
    }

    @Override
    public void insertRow(String table, HashMap<String, Object> javaObjectColumns, String primaryKeyCol, String primaryKeyValue) throws SQLException, IOException {
        PreparedStatement ps = null;
        String sql = null;

        ArrayList<String> colNames = getArrayListFromHashMap(javaObjectColumns);
        int columns = javaObjectColumns.size() + 1; //Primary key + columns
        String questionMarks = createQuestionMarks(columns);
        String cols = convertColumnNamesToString(primaryKeyCol, colNames);
        sql = "insert into " + table + "(" + cols + ") values(" + questionMarks + ")";

        ps = myConnection.prepareStatement(sql);
        ps.setString(1, primaryKeyValue);
        int colNr = 2; //Primary key is always 1
        for (String column : javaObjectColumns.keySet()) {
            ps.setObject(colNr, javaObjectColumns.get(column));
            colNr++;
        }
        ps.executeUpdate();
    }


    @Override
    public Object getJavaObject(String table, String primaryKey, String primaryKeyValue, int primaryKeyValueInt) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;

        sql = "select javaObject from " + table + " where " + primaryKey + " = ?;";
        ps = myConnection.prepareStatement(sql);
        if(primaryKeyValueInt > 0) ps.setInt(1, primaryKeyValueInt);
        else ps.setString(1, primaryKeyValue);
        rs = ps.executeQuery();
        if (rs.next()) {
            ByteArrayInputStream bais;
            ObjectInputStream ins;
            bais = new ByteArrayInputStream(rs.getBytes("javaObject"));
            ins = new ObjectInputStream(bais);
            Object mc = ins.readObject();
            ins.close();
            return mc;
        }
        throw new Exception("Couldn't find row");
    }

    @Override
    public Game getGame(int gameId) throws Exception {
        return (Game) getJavaObject(TABLE_GAMES, GAMES_PRIMARY_KEY,"", gameId);
    }

    @Override
    public int insertGame(Game game) throws Exception {
        PreparedStatement ps = null;
        String sql = null;
        sql = "insert into games (player1, player2, javaObject) values(?,?,?)";
        ps = myConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, game.getPlayer1Nickname());
        ps.setString(2, game.getPlayer2Nickname());
        ps.setObject(3, game);
        int response = ps.executeUpdate();
        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (generatedKeys.next()) {
            int primaryKey = generatedKeys.getInt(1);
            game.setId(primaryKey);
            updateJavaObject("games", "gameId", primaryKey, game);
            return primaryKey;
        }
        return response;
    }


    @Override
    public void updateJavaObject(String table, String primaryKey, int primaryKeyValue, Object javaObject) throws Exception {
        PreparedStatement ps = null;
        String sql = null;
        sql = "update " + table + " set javaObject = ? where " + primaryKey + " = ?";
        ps = myConnection.prepareStatement(sql);
        ps.setObject(1, javaObject);
        ps.setInt(2, primaryKeyValue);
        int result = ps.executeUpdate();
        if (result < 1)
            throw new Exception("Failed to perform sql: " + ps.toString() + " no rows were affected...");
    }

    private String convertColumnNamesToString(String primaryKeyCol, ArrayList<String> colNames) {
        String cols = primaryKeyCol + ",";
        for (String col : colNames) {
            cols += col + ",";
        }
        cols = cols.substring(0, cols.length() - 1);
        return cols;
    }
    private ArrayList<String> getArrayListFromHashMap(HashMap<String, Object> javaObjectColumns) throws IOException {
        ArrayList<String> colNames = new ArrayList<String>();
        for (String column : javaObjectColumns.keySet()) {
            javaObjectColumns.put(column, convertJavaObjectToBytes(javaObjectColumns.get(column)));
            colNames.add(column);
        }
        return colNames;
    }

    private String createQuestionMarks(int columns) {
        String s = "";
        while (columns > 0) {
            if (columns == 1) {
                s += "?";
                break;
            }
            columns--;
            s += "?,";
        }
        return s;
    }

    private byte[] convertJavaObjectToBytes(Object javaObject) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(javaObject);
        oos.flush();
        oos.close();
        bos.close();

        return bos.toByteArray();
    }
}

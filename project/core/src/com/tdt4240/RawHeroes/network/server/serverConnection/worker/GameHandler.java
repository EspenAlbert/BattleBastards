package com.tdt4240.RawHeroes.network.server.serverConnection.worker;

import com.tdt4240.RawHeroes.createGame.factory.GameBuilding;
import com.tdt4240.RawHeroes.event.move.Move;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.BoardMover;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CellConverter;
import com.tdt4240.RawHeroes.network.server.database.DatabaseConnector;
import com.tdt4240.RawHeroes.network.server.serverConnection.player.Player;
import com.tdt4240.RawHeroes.network.server.serverConnection.worker.exceptions.GameNotFoundException;
import com.tdt4240.RawHeroes.network.server.serverConnection.worker.exceptions.NotYourGameException;
import com.tdt4240.RawHeroes.network.server.serverConnection.worker.exceptions.NotYourTurnException;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Games;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by espen1 on 05.03.2015.
 */
public class GameHandler implements IGameHandler{


    private final DatabaseConnector databaseConnector;
    private static GameHandler instance;

    private GameHandler() throws Exception {
        databaseConnector = DatabaseConnector.getInstance();
    }

    public static GameHandler getInstance() throws Exception {
        if(instance == null) {
            instance = new GameHandler();
        }
        return instance;
    }

    @Override
    public int createGame(String player1, String player2, Games gameType) throws Exception {
        Game game = GameBuilding.getInstance().createGame(gameType, player1, player2);
        return databaseConnector.insertGame(game);
    }
    public int deleteGame(int gameId) throws GameNotFoundException, NotYourGameException, SQLException{
        return databaseConnector.deleteGame(gameId);
    }

    @Override
    public int findGame(String player1, String player2) throws SQLException {
        return databaseConnector.findGame(player1, player2);
    }

    @Override
    public Game getGame(String username, int gameId) throws GameNotFoundException, NotYourGameException {
        try {
            Game game = databaseConnector.getGame(gameId);
            if(game.getPlayer1Nickname().equals(username) || game.getPlayer2Nickname().equals(username)) return game;
            throw new NotYourGameException();
        } catch (Exception e) {
            throw new GameNotFoundException();
        }
    }

    @Override
    public boolean checkPlayer(Player player) throws Exception {
        Player storedPlayer = (Player) databaseConnector.getJavaObject("players", "username", player.getUsername(), -1);
        return player.getPassword().equals(storedPlayer.getPassword());

    }

    @Override
    public boolean isActiveGame(int gameId) throws SQLException {
        return databaseConnector.isActiveGame(gameId);
    }

    @Override
    public void doMoves(int gameId, String username, ArrayList<Move> moves) throws NotYourGameException, GameNotFoundException, NotYourTurnException, Exception {
        Game game = getGame(username, gameId);
        boolean iAmPlayer1 = username.equals(game.getPlayer1Nickname());
        if(iAmPlayer1 != game.getNextTurnIsPlayer1()) throw new NotYourTurnException();
        //Move was ok...
        if(!iAmPlayer1) {//Means that player 2 has done moves
            convertMoves(moves, game.getBoard().getWidth(), game.getBoard().getHeight());
        }
        game.setNextTurnIsPlayer1(!game.getNextTurnIsPlayer1());//Set the turn to other player
        BoardMover mover = new BoardMover(game.getBoard());
        mover.executeMovesFromOtherPlayer(game.getLastMoves(), true);
        game.setLastMoves(moves);
        boolean won = iAmPlayer1 ? game.player1IsWinner() : game.player2IsWinner();
        if(won) {
            Player player = (Player) databaseConnector.getJavaObject(DatabaseConnector.TABLE_PLAYERS, DatabaseConnector.PLAYERS_PRIMARY_KEY, username, -1);
            player.increaseScore();
            databaseConnector.updateJavaObject(DatabaseConnector.TABLE_PLAYERS, DatabaseConnector.PLAYERS_PRIMARY_KEY, username, player);
        }
        databaseConnector.updateGame(game);
    }

    @Override
    public ArrayList<Integer> getGameIds(String username) throws SQLException {
        ArrayList<Integer> gameIds = databaseConnector.getAllKeys(username);
        return gameIds;
    }

    private  ArrayList<Move> convertMoves(ArrayList<Move> moves, int xLength, int yLength) {
        CellConverter converter = new CellConverter(xLength, yLength);
        for(Move move: moves) {
            move.convertPositions(converter);
        }
        return moves;
    }
}

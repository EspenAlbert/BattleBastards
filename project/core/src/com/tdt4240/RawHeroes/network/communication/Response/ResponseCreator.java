package com.tdt4240.RawHeroes.network.communication.Response;

import com.tdt4240.RawHeroes.createGame.boards.StandardBoard;
import com.tdt4240.RawHeroes.network.server.serverConnection.player.Player;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;

import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * Created by espen1 on 06.03.2015.
 */
public class ResponseCreator {
    public static ResponseMessage getLoginSuccess() {
        return new ResponseMessage(ResponseType.SUCCESS, "Logged in successfully!");
    }
    public static ResponseMessage getChangedPasswordSucess(){
        return new ResponseMessage(ResponseType.SUCCESS, "You have sucessfully changed your password");
    }
    public static ResponseMessage getChangedPasswordFailed(){
        return new ResponseMessage(ResponseType.FAILURE, "Something wrong happened!");
    }
    public static ResponseMessage getCreateUserSuccess() {
        return new ResponseMessage(ResponseType.SUCCESS, "Username successfully created!");
    }
    public static ResponseMessage getWrongUsernamePassword() {
        return new ResponseMessage(ResponseType.FAILURE, "Wrong usename / password");
    }
    public static ResponseMessage getUsernameNotAvailable() {
        return new ResponseMessage(ResponseType.FAILURE, "Username is not unique");
    }
    public static ResponseMessage getFailedToCreateUser() {
        return new ResponseMessage(ResponseType.FAILURE, "Failed to create user");
    }
    public static ResponseMessage getDeletedGame(){
        return new ResponseMessage(ResponseType.SUCCESS, "Deleted game from database");
    }

    public static ResponseMessage getChallengePlayerDoesNotExist() {
        return new ResponseMessage(ResponseType.FAILURE, "Challenged player doesn't exist!");
    }

    public static ResponseMessage getGameAlreadyExist(String otherPlayer) {
        return new ResponseMessage(ResponseType.FAILURE, "There is already a game existing between you and " + otherPlayer);
    }

    public static ResponseMessage getCreateGameSuccess(int gameId) {
        return new ResponseMessage(ResponseType.SUCCESS, gameId);
    }

    public static ResponseMessage getFailedToCreateGame() {
        return new ResponseMessage(ResponseType.FAILURE, "Something went wrong during the creation of the game");
    }

    public static ResponseMessage getInvalidGameException(int gameId) {
        return new ResponseMessage(ResponseType.FAILURE, "The game with id: " + gameId + " have been deleted, or doesn't exist!");
    }

    public static ResponseMessage getDoMovesSuccess(int gameId) {
        return new ResponseMessage(ResponseType.SUCCESS, "You successfully did moves to game: " + gameId);
    }

    public static ResponseMessage getNotYourGameException(int gameId) {
        return new ResponseMessage(ResponseType.FAILURE, "You tried to do moves to game: " + gameId + ", but this game is not yours!!");
    }

    public static ResponseMessage getNotYourTurnException(int gameId) {
        return new ResponseMessage(ResponseType.FAILURE, "It is not your turn to do a move! on game: " + gameId);
    }

    public static ResponseMessage getGameSuccess(Game game) {
        return new ResponseMessage(ResponseType.SUCCESS, game);
    }
    public static ResponseMessage testBoardResponse(StandardBoard board) {
        return new ResponseMessage(ResponseType.SUCCESS, board);
    }

    public static ResponseMessage getGameIds(ArrayList<Integer> gameIds) {
        return new ResponseMessage(ResponseType.SUCCESS, gameIds);
    }
    public static ResponseMessage getFriendListSuccess(ArrayList<Player> friends){
        return new ResponseMessage(ResponseType.SUCCESS, friends);
    }
    public static ResponseMessage getFriendListFailure(){
        return new ResponseMessage(ResponseType.FAILURE, "You have no friends yet");
    }

    public static ResponseMessage getAddedSucess() {
        return new ResponseMessage(ResponseType.SUCCESS, "Added friend to list");
    }
    public static ResponseMessage getAddedFailure(){
        return new ResponseMessage(ResponseType.FAILURE, "Friend already in friendlist");
    }
}

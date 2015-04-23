package com.tdt4240.RawHeroes.topLayer.screens;

import com.tdt4240.RawHeroes.createGame.factory.GameBuilding;
import com.tdt4240.RawHeroes.network.client.ClientConnection;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Games;
import com.tdt4240.RawHeroes.topLayer.launcher.BattleBastards;
import com.tdt4240.RawHeroes.independent.MyInputProcessor;

import java.util.Stack;

/**
 * Created by espen1 on 27.02.2015.
 */
public class ScreenStateManager {

    private BattleBastards game;
    private Stack<ScreenState> screenStates;
    private boolean popMe;


    public static final int MAINMENU = 912837;
    public static final int GAMESCREEN = 2;

    public ScreenStateManager(BattleBastards game) {
        this.game = game;
        screenStates = new Stack<ScreenState>();
        //pushState(GAMESCREEN);
        pushState(new LoginScreen(this));
        //pushState(new ActiveGameScreen(this, GameBuilding.getInstance().createGame(Games.KILL_ALL_ENEMY_UNITS, "Player1", "Player2")));
    }
    public BattleBastards getGame() {
        return game;
    }


    public void update(float dt) {
        screenStates.peek().update(dt);
    }
    public void render() {
        screenStates.peek().render();
        if (popMe){
            ScreenState g = screenStates.pop();
            g.dispose();
            MyInputProcessor.getInstance().removeListeners();
            popMe = false;
            screenStates.peek().setInputProcessor();
            screenStates.peek().setMsg();
        }
    }
    public ScreenState peek(int i){
        return screenStates.get(i);
    }

    private ScreenState getState(int state) {
        if(state == MAINMENU) return new MainMenuScreen(this);
        else if(state == GAMESCREEN) return new ActiveGameScreen(this, GameBuilding.getInstance().createGame(Games.KILL_ALL_ENEMY_UNITS, "player1", "player2"));
        /*
        else if(state == TASK2) return new Task2State(this);
        else if(state == TASK3) return new Task3State(this);
        else if(state == TASK4) return new Task4State(this);
        else if(state == END_GAME_SCREEN) return new EndGameState(this, ScoreBoard.player1Won);
        */
        return null;
    }

    public void setState(int state) {
        System.out.println(screenStates.peek());
        popState();
        pushState(state);
    }
    public void setState(ScreenState newState) {
        popState();
        pushState(newState);
    }

    public void pushState(ScreenState newState) {
        screenStates.push(newState);
    }


    public void pushState(int state) {
        screenStates.push(getState(state));
    }

    public void popState() {
        ScreenState g = screenStates.pop();
        g.dispose();
        MyInputProcessor.getInstance().removeListeners();
    }
    public void popOnly(){
        popMe = true;

    }

    public void resize(int width, int height) {
        screenStates.peek().resize(width, height);
    }
}


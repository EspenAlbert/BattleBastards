package com.tdt4240.RawHeroes.topLayer.screens;

import com.tdt4240.RawHeroes.topLayer.launcher.BattleBastards;
import com.tdt4240.RawHeroes.independent.MyInputProcessor;

import java.util.Stack;

/**
 * Created by espen1 on 27.02.2015.
 */
public class ScreenStateManager {

    private BattleBastards game;
    private Stack<ScreenState> screenStates;


    public static final int MAINMENU = 912837;
    public static final int TASK2 = 912838;
    public static final int TASK3 = 912839;
    public static final int TASK4 = 912840;
    public static final int END_GAME_SCREEN = 912841;

    public ScreenStateManager(BattleBastards game) {
        this.game = game;
        screenStates = new Stack<ScreenState>();

        pushState(new LoginScreen(this));
    }
    public BattleBastards getGame() {
        return game;
    }


    public void update(float dt) {
        screenStates.peek().update(dt);
    }
    public void render() {
        screenStates.peek().render();

    }

    private ScreenState getState(int state) {
        if(state == MAINMENU) return new MainMenuScreen(this);
        /*
        else if(state == TASK2) return new Task2State(this);
        else if(state == TASK3) return new Task3State(this);
        else if(state == TASK4) return new Task4State(this);
        else if(state == END_GAME_SCREEN) return new EndGameState(this, ScoreBoard.player1Won);
        */
        return null;
    }

    public void setState(int state) {
        popState();
        pushState(state);
    }
    public void setState(ScreenState newState) {
        popState();
        pushState(newState);
    }

    private void pushState(ScreenState newState) {
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
}


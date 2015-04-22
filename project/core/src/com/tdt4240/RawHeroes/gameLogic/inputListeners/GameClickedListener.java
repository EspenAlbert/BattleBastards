package com.tdt4240.RawHeroes.gameLogic.inputListeners;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tdt4240.RawHeroes.topLayer.screens.MainMenuScreen;
import com.tdt4240.RawHeroes.topLayer.screens.ScreenState;

/**
 * Created by Håvard on 19.04.2015.
 */
public class GameClickedListener extends ClickListener {
    private MainMenuScreen screenState = null;
    private int gameId = 0;

    public GameClickedListener(MainMenuScreen screenState, int gameId){
        this.screenState = screenState;
        this.gameId = gameId;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        screenState.gameSelected(gameId);
    }
}

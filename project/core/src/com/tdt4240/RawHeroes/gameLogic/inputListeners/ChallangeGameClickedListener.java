package com.tdt4240.RawHeroes.gameLogic.inputListeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tdt4240.RawHeroes.topLayer.screens.CreateGameScreen;

/**
 * Created by Håvard on 20.04.2015.
 */
public class ChallangeGameClickedListener extends ClickListener {
    private CreateGameScreen screenState = null;
    private String opponent;

    public ChallangeGameClickedListener(CreateGameScreen screenState, String opponent){
        this.screenState = screenState;
        this.opponent = opponent;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        screenState.createGameButtonClicked(opponent);
    }
}

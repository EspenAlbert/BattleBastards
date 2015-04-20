package com.tdt4240.RawHeroes.topLayer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.tdt4240.RawHeroes.view.uiElements.LabelFactory;

/**
 * Created by Håvard on 19.04.2015.
 */
public class SettingScreen extends ScreenState {
    private Stage stage;
    private Label title;


    public SettingScreen(ScreenStateManager gsm) {
        super(gsm);

        stage = new Stage();
        title = LabelFactory.createLabel("title", 300,400);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {

    }

    @Override
    public void dispose() {

    }
}

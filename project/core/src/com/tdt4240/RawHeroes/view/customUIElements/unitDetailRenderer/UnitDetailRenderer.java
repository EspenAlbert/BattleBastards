package com.tdt4240.RawHeroes.view.customUIElements.unitDetailRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.independent.GameConstants;

/**
 * Created by William on 18.04.2015.
 */
public class UnitDetailRenderer {

    private final Dialog createUnitDetailsScreenDialog;
    private Skin skin;

    public UnitDetailRenderer(IUnit unit){
        skin = new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas(Gdx.files.internal("uiskin.atlas")));

        System.out.println("Clicked a unit for detailed information...");
        createUnitDetailsScreenDialog = new Dialog("Unit Details", skin);
        createUnitDetailsScreenDialog.setBounds(50, 25, GameConstants.RESOLUTION_WIDTH - 100, GameConstants.RESOLUTION_HEIGHT - 50);
        final TextField textFieldUnit = new TextField(String.valueOf(unit.getIdentifier()), skin);
        textFieldUnit.setSize(GameConstants.TEXTFIELD_WIDTH, GameConstants.TEXTFIELD_HEIGHT);
        textFieldUnit.setPosition(createUnitDetailsScreenDialog.getWidth()/2 - textFieldUnit.getWidth()/2, createUnitDetailsScreenDialog.getHeight()/2);
        createUnitDetailsScreenDialog.addActor(textFieldUnit);

        TextButton exitButton = new TextButton("Exit", skin);
        exitButton.setSize(GameConstants.TEXTFIELD_WIDTH, GameConstants.TEXTFIELD_HEIGHT);
        exitButton.setPosition(GameConstants.RESOLUTION_WIDTH-400, 200);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                createUnitDetailsScreenDialog.remove();
            }
        });
        createUnitDetailsScreenDialog.addActor(exitButton);
    }
}

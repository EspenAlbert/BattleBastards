package com.tdt4240.RawHeroes.view.uiElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.tdt4240.RawHeroes.independent.GameConstants;

/**
 * Created by Håvard on 19.04.2015.
 */
public class TextFieldFactory {
    private static Skin skin = new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas(Gdx.files.internal("uiskin.atlas")));

    public static TextField createTextField(String text, int xPos, int yPos) {
        TextField textField = new TextField(text, skin);
        textField.setSize(GameConstants.TEXTFIELD_WIDTH, GameConstants.TEXTFIELD_HEIGHT);
        textField.setPosition(xPos, yPos);
        textField.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());

        return textField;
    }
}

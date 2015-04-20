package com.tdt4240.RawHeroes.view.uiElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.tdt4240.RawHeroes.independent.GameConstants;

/**
 * Created by Håvard on 20.04.2015.
 */
public class CheckBoxFactory {
    private static Skin skin = new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas(Gdx.files.internal("uiskin.atlas")));

    public static CheckBox createCheckBox(String text, int xPos, int yPos) {
        CheckBox checkBox = new CheckBox(text, skin);
        checkBox.getCells().get(0).size(GameConstants.BUTTON_HEIGHT, GameConstants.BUTTON_HEIGHT);
        checkBox.setPosition(xPos - checkBox.getWidth(), yPos);

        return checkBox;
    }
}

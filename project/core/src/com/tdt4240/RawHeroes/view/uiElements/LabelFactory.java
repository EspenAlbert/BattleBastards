package com.tdt4240.RawHeroes.view.uiElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.tdt4240.RawHeroes.independent.GameConstants;

/**
 * Created by Håvard on 19.04.2015.
 */
public class LabelFactory {
    private static Skin skin = new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas(Gdx.files.internal("uiskin.atlas")));

    public static Label createLabel(String text, int xPos, int yPos) {
        Label label = new Label(text, skin);
        label.setSize(GameConstants.LABEL_WIDTH, GameConstants.LABEL_HEIGHT);
        label.setPosition(xPos, yPos);

        return label;
    }
}

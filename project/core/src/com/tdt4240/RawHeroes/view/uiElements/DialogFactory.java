package com.tdt4240.RawHeroes.view.uiElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.tdt4240.RawHeroes.independent.GameConstants;

/**
 * Created by Håvard on 19.04.2015.
 */
public class DialogFactory {
    private static Skin skin = new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas(Gdx.files.internal("uiskin.atlas")));

    public static Dialog createDialogFactory(String text){
        final Dialog createGameDialog = new Dialog(text, skin);
        createGameDialog.setBounds(50,25, GameConstants.RESOLUTION_WIDTH - 100, GameConstants.RESOLUTION_HEIGHT- 50);

        return createGameDialog;
    }
}

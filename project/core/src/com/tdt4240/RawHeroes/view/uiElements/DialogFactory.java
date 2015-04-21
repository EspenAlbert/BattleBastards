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
    public static final int DIALOG_WIDTH = GameConstants.RESOLUTION_WIDTH - 100;
    public static final int DIALOG_HEIGHT = GameConstants.RESOLUTION_HEIGHT- 50;
    public static final int DIALOG_XPOS = 50;
    public static final int DIALOG_YPOS = 25;

    public static Dialog createDialogFactory(String text){
        return createDialogFactory(text, DIALOG_XPOS, DIALOG_YPOS,DIALOG_WIDTH ,DIALOG_HEIGHT);
    }
    public static Dialog createDialogFactory(String text, int x, int y, int xSize, int ySize){
        final Dialog createGameDialog = new Dialog(text, skin);
        createGameDialog.setBounds(x, y,xSize ,ySize );
        return createGameDialog;
    }
}

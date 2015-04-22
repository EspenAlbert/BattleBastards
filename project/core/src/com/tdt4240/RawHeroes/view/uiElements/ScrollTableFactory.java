package com.tdt4240.RawHeroes.view.uiElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by Håvard on 19.04.2015.
 */
public class ScrollTableFactory {
    private static Skin skin = new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas(Gdx.files.internal("uiskin.atlas")));

    public static Table createScrollTable(){
        Table scroll = new Table(skin);
        return scroll;
    }
}

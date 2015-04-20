package com.tdt4240.RawHeroes.view.uiElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.tdt4240.RawHeroes.independent.GameConstants;

/**
 * Created by Håvard on 19.04.2015.
 */
public class ScrollPaneFactory {

    public static ScrollPane createScrollPane(Table scrollTable){
        ScrollPane scrollPane = new ScrollPane(scrollTable);
        scrollPane.setBounds(GameConstants.RESOLUTION_WIDTH / 2, 0, GameConstants.RESOLUTION_WIDTH / 2, GameConstants.RESOLUTION_HEIGHT - GameConstants.RESOLUTION_HEIGHT/7);

        return scrollPane;
    }
}

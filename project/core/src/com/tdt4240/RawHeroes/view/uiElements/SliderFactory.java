package com.tdt4240.RawHeroes.view.uiElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.tdt4240.RawHeroes.independent.GameConstants;

/**
 * Created by Håvard on 21.04.2015.
 */
public class SliderFactory {
    private static Skin skin = new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas(Gdx.files.internal("uiskin.atlas")));

    public static Slider createSlider(){
        Slider slider  = new Slider(1, 2, 0.1f, false, skin);
        slider.setValue((GameConstants.CAMERA_SPEED /0.07f));
        slider.setAnimateDuration(0.1f);

        return slider;

    }
}

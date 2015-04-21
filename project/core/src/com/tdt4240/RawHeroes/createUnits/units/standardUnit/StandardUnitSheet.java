package com.tdt4240.RawHeroes.createUnits.units.standardUnit;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tdt4240.RawHeroes.event.events.AnimationEvent;
import com.tdt4240.RawHeroes.event.listener.IAnimationListener;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitAnimationController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.SimpleUnitAnimationController;
import com.tdt4240.RawHeroes.gameLogic.models.ISpritesheet;

import java.io.Serializable;

/**
 * Created by Endre on 20.04.2015.
 */
public class StandardUnitSheet implements ISpritesheet, Serializable{
    private Texture sheet;
    private final int NR_OF_FRAMES = 4;
    private final int NR_OF_ANIMATIONS = 9;

    public StandardUnitSheet(String textureLocation){
        this.sheet = new Texture(Gdx.files.internal(textureLocation));
    }


    @Override
    public TextureRegion getActiveFrame(int activeFrame, int activeAnimation) {
        int frameWidth = sheet.getWidth()/NR_OF_FRAMES;
        int frameHeight = sheet.getHeight()/NR_OF_ANIMATIONS;
        TextureRegion frame = new TextureRegion(sheet, activeFrame*frameWidth, activeAnimation*frameHeight, frameWidth, frameHeight);
        return frame;
    }

    @Override
    public void setTexture(Texture texture) {
        this.sheet = texture;
    }

    @Override
    public Texture getTexture() {
        return this.sheet;
    }
}

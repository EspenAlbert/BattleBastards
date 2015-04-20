package com.tdt4240.RawHeroes.gameLogic.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tdt4240.RawHeroes.event.listener.IAnimationListener;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitAnimationController;

/**
 * Created by Endre on 20.04.2015.
 */
public interface ISpritesheet{
    public TextureRegion getActiveFrame(int activeFrame, int activeAnimation);
    public void setTexture(Texture texture);
    public Texture getTexture();
}

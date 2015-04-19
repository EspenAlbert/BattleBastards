package com.tdt4240.RawHeroes.gameLogic.controllers.unitController;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Endre on 19.04.2015.
 */
public interface IUnitAnimationController {
    TextureRegion getActiveFrame(Texture texture);
}

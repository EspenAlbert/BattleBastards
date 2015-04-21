package com.tdt4240.RawHeroes.gameLogic.controllers.unitController;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tdt4240.RawHeroes.event.events.AnimationEvent;
import com.tdt4240.RawHeroes.event.listener.IAnimationListener;

/**
 * Created by Endre on 19.04.2015.
 */
public interface IUnitAnimationController {
    TextureRegion getActiveFrame(Texture texture);
    void setActiveAnimation(int activeAnimation);
    void setActiveFrame(int activeFrame);
    int getActiveAnimation();
    void nextFrame();
    void addAnimationListener(IAnimationListener listener);
}

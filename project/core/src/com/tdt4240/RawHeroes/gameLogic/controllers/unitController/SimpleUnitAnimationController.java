package com.tdt4240.RawHeroes.gameLogic.controllers.unitController;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Endre on 19.04.2015.
 */
public class SimpleUnitAnimationController implements IUnitAnimationController{
    private int activeAnimation;
    private int activeFrame;

    private final int NR_OF_FRAMES = 1;
    private final int NR_OF_ANIMATIONS = 8;

    @Override
    public TextureRegion getActiveFrame(Texture texture) {
        int frameWidth = texture.getWidth()/NR_OF_FRAMES;
        int frameHeight = texture.getHeight()/NR_OF_ANIMATIONS;
        TextureRegion frame = new TextureRegion(texture, activeFrame*frameWidth, activeAnimation*frameHeight, frameWidth, frameHeight);
        return frame;

    }

    public void setActiveAnimation(int activeAnimation){
        this.activeAnimation = activeAnimation;
    }
    public void setActiveFrame(int activeFrame){
        this.activeFrame = activeFrame;
    }
    public void increaseActiveFrame(){
        this.activeFrame++;
    }
}

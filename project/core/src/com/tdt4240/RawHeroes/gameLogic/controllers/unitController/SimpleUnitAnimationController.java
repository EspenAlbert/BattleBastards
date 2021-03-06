package com.tdt4240.RawHeroes.gameLogic.controllers.unitController;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tdt4240.RawHeroes.event.events.AnimationEvent;
import com.tdt4240.RawHeroes.event.listener.IAnimationListener;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.gameLogic.models.UnitRenderModel;
import com.tdt4240.RawHeroes.independent.AnimationConstants;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.RenderMode;

import java.io.Serializable;
import java.util.ArrayList;

import static com.tdt4240.RawHeroes.independent.AnimationConstants.*;

/**
 * Created by Endre on 19.04.2015.
 */
public class SimpleUnitAnimationController implements IUnitAnimationController, Serializable{
    private int activeAnimation;
    private int activeFrame;

    private ArrayList<IAnimationListener> listeners;

    private final int NR_OF_FRAMES = 4;
    private final int NR_OF_ANIMATIONS = 9;
    private IUnit unit;
    private UnitRenderModel unitRenderModel;

    public SimpleUnitAnimationController(IUnit unit, UnitRenderModel unitRenderModel){
        this.unit = unit;
        this.unitRenderModel = unitRenderModel;
        this.activeAnimation = 0;
        this.activeFrame = 0;
        listeners = new ArrayList<IAnimationListener>();
    }
    @Override
    public TextureRegion getActiveFrame(Texture texture) {
        int frameWidth = texture.getWidth()/NR_OF_FRAMES;
        int frameHeight = texture.getHeight()/NR_OF_ANIMATIONS;
        TextureRegion frame = new TextureRegion(texture, activeFrame*frameWidth, activeAnimation*frameHeight, frameWidth, frameHeight);
        return frame;

    }

    public void setActiveAnimation(RenderMode renderMode){
        switch (renderMode){
            case STATIC:
                if (getActiveAnimation() == AnimationConstants.MOVE_RIGHT)setActiveAnimation(AnimationConstants.IDLE_RIGHT);
                if (getActiveAnimation() == AnimationConstants.MOVE_LEFT)setActiveAnimation(AnimationConstants.IDLE_LEFT);
                break;
            case MOVING:
                if(unitRenderModel.isTurnedRight())setActiveAnimation(AnimationConstants.MOVE_RIGHT);
                else setActiveAnimation(AnimationConstants.MOVE_LEFT);
                break;
            case ATTACKING:
                if(unitRenderModel.isTurnedRight())setActiveAnimation(AnimationConstants.ATK_RIGHT);
                else setActiveAnimation(AnimationConstants.ATK_LEFT);
                break;
            case HURT:
                if (getActiveAnimation() == AnimationConstants.IDLE_RIGHT)setActiveAnimation(AnimationConstants.HURT_RIGHT);
                else setActiveAnimation(AnimationConstants.HURT_LEFT);
                break;
            case KILLED:
                setActiveAnimation(AnimationConstants.DEAD);
                break;
        }
    }

    public void setActiveAnimation(int activeAnimation){
        if (activeAnimation >= NR_OF_ANIMATIONS) this.activeAnimation = NR_OF_ANIMATIONS - 1;
        else if (activeAnimation < 0) this.activeAnimation = 0;
        else this.activeAnimation = activeAnimation;
        this.activeFrame = 0;
        fireAnimationChanged(new AnimationEvent(this.activeAnimation, this.activeFrame));
    }
    public void setActiveFrame(int activeFrame){
        if (activeFrame >= NR_OF_FRAMES) this.activeFrame = NR_OF_FRAMES - 1;
        else if (activeFrame < 0) this.activeFrame = 0;
        else this.activeFrame = activeFrame;
        fireAnimationChanged(new AnimationEvent(this.activeAnimation, this.activeFrame));
    }
    public void nextFrame(){
        this.activeFrame++;
        if(this.activeFrame >= NR_OF_FRAMES){
            this.activeFrame = 0;
            switch (this.activeAnimation){
                case ATK_LEFT:
                    setActiveAnimation(IDLE_LEFT);
                    break;
                case ATK_RIGHT:
                    setActiveAnimation(IDLE_RIGHT);
                    break;

                case IDLE_LEFT: break;
                case IDLE_RIGHT: break;
                case DEAD: break;

                case HURT_LEFT:
                    if(unit.getHealth() < 1) setActiveAnimation(DEAD);
                    else setActiveAnimation(IDLE_LEFT);
                    break;
                case HURT_RIGHT:
                    if(unit.getHealth() < 1) setActiveAnimation(DEAD);
                    setActiveAnimation(IDLE_RIGHT);
                    break;

                case MOVE_LEFT: break;
                case MOVE_RIGHT: break;
            }
        }
        fireAnimationChanged(new AnimationEvent(this.activeAnimation, this.activeFrame));
    }

    @Override
    public void addAnimationListener(IAnimationListener listener){
        this.listeners.add(listener);
    }
    private void fireAnimationChanged(AnimationEvent event){
        for (IAnimationListener listener : this.listeners){
            listener.animationChanged(event);
        }
    }

    @Override
    public int getActiveAnimation(){
        return this.activeAnimation;
    }


}

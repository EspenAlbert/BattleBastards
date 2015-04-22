package com.tdt4240.RawHeroes.gameLogic.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tdt4240.RawHeroes.createUnits.units.axeUnit.AxeUnitSheet;
import com.tdt4240.RawHeroes.createUnits.units.standardUnit.StandardUnitSheet;
import com.tdt4240.RawHeroes.event.listener.IAnimationListener;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitAnimationController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.SimpleUnitAnimationController;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;
import com.tdt4240.RawHeroes.independent.AnimationConstants;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.RenderMode;

/**
 * Created by Endre on 22.04.2015.
 */
public class UnitRenderModel {

    private IUnitAnimationController unitAnimationController;
    private ISpritesheet sheet;
    private boolean turnedRight;
    private boolean isRed;


    public UnitRenderModel(){
        this(UnitName.STANDARD_UNIT, true);
    }public UnitRenderModel(UnitName name, boolean isRed){
        this.unitAnimationController = new SimpleUnitAnimationController();
        if(!isRed){
            this.unitAnimationController.setActiveAnimation(AnimationConstants.IDLE_LEFT);
        }
        switch (name){
            case STANDARD_UNIT:
                this.sheet = new StandardUnitSheet("units/soldierSheet.png");
                break;
            case UNIT2:
                this.sheet = new StandardUnitSheet("units/soldierSheet.png");
                break;
            case STANDARD_UNIT_2:
                this.sheet = new AxeUnitSheet("units/soldierAxeSheet.png");
                break;
            default:
                this.sheet = new StandardUnitSheet("units/soldierSheet.png");
                break;
        }
        turnedRight = isRed;
        this.isRed = isRed;

    }

    public void setActiveAnimation(RenderMode renderMode){
        switch (renderMode){

            case STATIC:
                if (this.unitAnimationController.getActiveAnimation() == AnimationConstants.MOVE_RIGHT)this.unitAnimationController.setActiveAnimation(AnimationConstants.IDLE_RIGHT);
                if (this.unitAnimationController.getActiveAnimation() == AnimationConstants.MOVE_LEFT)this.unitAnimationController.setActiveAnimation(AnimationConstants.IDLE_LEFT);
                break;
            case MOVING:
                if(this.turnedRight)this.unitAnimationController.setActiveAnimation(AnimationConstants.MOVE_RIGHT);
                else this.unitAnimationController.setActiveAnimation(AnimationConstants.MOVE_LEFT);
                break;
            case ATTACKING:
                if(this.turnedRight)this.unitAnimationController.setActiveAnimation(AnimationConstants.ATK_RIGHT);
                else this.unitAnimationController.setActiveAnimation(AnimationConstants.ATK_LEFT);
                break;
            case HURT:
                if (this.unitAnimationController.getActiveAnimation() == AnimationConstants.IDLE_RIGHT)this.unitAnimationController.setActiveAnimation(AnimationConstants.HURT_RIGHT);
                else this.unitAnimationController.setActiveAnimation(AnimationConstants.HURT_LEFT);
                break;
            case KILLED:
                this.unitAnimationController.setActiveAnimation(AnimationConstants.DEAD);
                break;
        }
    }

    public void addAnimationListener(IAnimationListener animationListener) {
        this.unitAnimationController.addAnimationListener(animationListener);
    }

    public TextureRegion getActiveFrame(){
       return this.unitAnimationController.getActiveFrame(this.sheet.getTexture());
    }

    public void nextFrame(){
        this.unitAnimationController.nextFrame();
    }

    public ISpritesheet getSheet(){
        return this.sheet;
    }

    public boolean isRed(){
        return this.isRed;
    }


    //TODO trenger disse å være her?
    public boolean isTurnedRight() {
        return turnedRight;
    }

    public void turnDirection() {
        this.turnedRight = !turnedRight;
    }
}

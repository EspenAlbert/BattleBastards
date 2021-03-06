package com.tdt4240.RawHeroes.gameLogic.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tdt4240.RawHeroes.createUnits.units.StandardSheet;
import com.tdt4240.RawHeroes.event.listener.IAnimationListener;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitAnimationController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.SimpleUnitAnimationController;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;
import com.tdt4240.RawHeroes.independent.AnimationConstants;
import com.tdt4240.RawHeroes.independent.TextureChanger;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.RenderMode;

/**
 * Created by Endre on 22.04.2015.
 */
public class UnitRenderModel {

    private IUnitAnimationController unitAnimationController;
    private ISpritesheet sheet;
    private boolean turnedRight;
    private boolean isRed;

    public UnitRenderModel(IUnit unit, boolean iAmPlayer1){
        UnitName name = unit.getIdentifier();
        this.unitAnimationController = new SimpleUnitAnimationController(unit, this);
        isRed = iAmPlayer1 == unit.isPlayer1Unit();
        if(!isRed){
            this.unitAnimationController.setActiveAnimation(AnimationConstants.IDLE_LEFT);
        }
        if(unit.getHealth() < 1) {
            unitAnimationController.setActiveAnimation(AnimationConstants.DEAD);
        }
        switch (name){
            case STANDARD_UNIT:
                this.sheet = new StandardSheet("units/soldierSheet.png");
                break;
            case UNIT2:
                this.sheet = new StandardSheet("units/soldierSheet.png");
                break;
            case STANDARD_UNIT_2:
                this.sheet = new StandardSheet("units/soldierAxeSheet.png");
                break;
            default:
                this.sheet = new StandardSheet("units/soldierSheet.png");
                break;
        }
        if(isRed) {
            sheet.setTexture (TextureChanger.changeColor(sheet.getTexture(), Color.RED));
        } else {
            sheet.setTexture (TextureChanger.changeColor(sheet.getTexture(), Color.BLUE));
        }
        turnedRight = isRed;

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

    public IUnitAnimationController getUnitAnimationController() {
        return unitAnimationController;
    }
}

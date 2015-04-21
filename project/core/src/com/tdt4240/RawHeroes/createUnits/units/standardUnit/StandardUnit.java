package com.tdt4240.RawHeroes.createUnits.units.standardUnit;


import com.tdt4240.RawHeroes.createUnits.units.Unit;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tdt4240.RawHeroes.event.listener.IAnimationListener;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.IBoardController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitAnimationController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitCombatController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitMovementController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.SimpleUnitAnimationController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.SimpleUnitCombatController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.WalkingUnitMovementController;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.gameLogic.models.ISpritesheet;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;
import com.tdt4240.RawHeroes.independent.AnimationConstants;
import com.tdt4240.RawHeroes.independent.Position;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.RenderMode;

import java.util.ArrayList;

/**
 * Created by espen1 on 07.03.2015.
 */
public class StandardUnit extends Unit {

    private final int MAX_HEALTH = 20;

    private final int MIN_DMG = 5;
    private final int MAX_DMG = 10;
    private final int MAX_MOVES = 3;
    private final IUnitAnimationController unitAnimationController;

    private boolean turnedRight;


    public StandardUnit(boolean player1Unit) {
        super(player1Unit);
        health = MAX_HEALTH;
        this.remainingMoves = MAX_MOVES;
        this.weight = 10;
        this.unitCombatController = new SimpleUnitCombatController(this, MIN_DMG, MAX_DMG, 1, MAX_HEALTH, MAX_MOVES);
        this.turnedRight = player1Unit;
        this.remainingMoves = 3;
        this.weight = 5;
        this.unitMoveController = new WalkingUnitMovementController();
        this.unitAnimationController = new SimpleUnitAnimationController();
        if(!turnedRight){
            this.unitAnimationController.setActiveAnimation(AnimationConstants.IDLE_LEFT);
        }
        System.out.println("Created a standard unit");
    }
    private StandardUnit(boolean player1Unit, int health, boolean hasAttacked, IUnitCombatController unitCombatController, IUnitMovementController unitMoveController, int remainingMoves, int weight, IUnitAnimationController unitAnimationController) {
        super(player1Unit, health, hasAttacked, unitCombatController, unitMoveController, remainingMoves, weight);


        this.unitAnimationController = unitAnimationController;
    }

    @Override
    public UnitName getIdentifier() {
        return UnitName.STANDARD_UNIT;
    }

    @Override
    public boolean isTurnedRight() {
        return turnedRight;
    }

    @Override
    public void turnDirection() {
        this.turnedRight = !turnedRight;
    }

    public IUnit getCopy() {
        return new StandardUnit(player1Unit, health, hasAttacked, unitCombatController, unitMoveController,remainingMoves, weight, unitAnimationController);
    }

    @Override
    public TextureRegion getActiveFrame(Texture texture){
       return this.unitAnimationController.getActiveFrame(texture);
    }

    @Override
    public void nextFrame(){
        this.unitAnimationController.nextFrame();
    }

    @Override
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

    @Override
    public void resetMoves() {
        unitCombatController.resetMoves();
    }

    @Override
    public int[] getAttackDmg() {
        return new int[]{this.unitCombatController.getMinAttackDmg(), this.unitCombatController.getMaxAttackDmg()};
    }

    @Override
    public int getArmor() {
        return this.unitCombatController.getArmor();
    }

    @Override
    public int getMaxHealth() {
        return this.unitCombatController.getMaxHealth();
    }

    @Override
    public int getMovesLeft() {
        return this.unitCombatController.getMovesLeft();
    }

    @Override
    public void move(int movesSpent) {
        this.unitCombatController.move(movesSpent);
    }

    @Override
    public void addAnimationListener(IAnimationListener animationListener) {
        this.unitAnimationController.addAnimationListener(animationListener);
    }
}

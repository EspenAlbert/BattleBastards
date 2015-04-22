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

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by espen1 on 07.03.2015.
 */
public class StandardUnit extends Unit implements Serializable {

    private final int MAX_HEALTH = 20;

    private final int MIN_DMG = 5;
    private final int MAX_DMG = 10;
    private final int MAX_MOVES = 3;

    private boolean turnedRight;


    public StandardUnit(boolean player1Unit) {
        super(player1Unit, 10);
        health = MAX_HEALTH;
        this.remainingMoves = MAX_MOVES;
        this.unitCombatController = new SimpleUnitCombatController(this, MIN_DMG, MAX_DMG, 1, MAX_HEALTH);
        this.unitMoveController = new WalkingUnitMovementController();
        this.weight = 10;
        this.turnedRight = player1Unit;
        /*this.unitAnimationController = new SimpleUnitAnimationController();
        if(!turnedRight){
            this.unitAnimationController.setActiveAnimation(AnimationConstants.IDLE_LEFT);
        }*/
        System.out.println("Created a standard unit");
    }
    private StandardUnit(boolean player1Unit, int health, boolean hasAttacked, IUnitCombatController unitCombatController, IUnitMovementController unitMoveController, int remainingMoves, int weight) {
        super(player1Unit, health, hasAttacked, unitCombatController, unitMoveController, remainingMoves, weight);
    }

    @Override
    public UnitName getIdentifier() {
        return UnitName.STANDARD_UNIT;
    }


    public IUnit getCopy() {
        return new StandardUnit(player1Unit, health, hasAttacked, unitCombatController, unitMoveController,remainingMoves, weight);
    }


    @Override
    public int getRemainingMoves() {
        return remainingMoves;
    }

    @Override
    public void resetMoves() {
        remainingMoves = MAX_MOVES;
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
}

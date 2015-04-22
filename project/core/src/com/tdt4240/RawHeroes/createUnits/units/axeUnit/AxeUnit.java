package com.tdt4240.RawHeroes.createUnits.units.axeUnit;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tdt4240.RawHeroes.createUnits.units.Unit;
import com.tdt4240.RawHeroes.event.listener.IAnimationListener;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitAnimationController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitCombatController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitMovementController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.SimpleUnitAnimationController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.SimpleUnitCombatController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.WalkingUnitMovementController;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;
import com.tdt4240.RawHeroes.independent.AnimationConstants;
import com.tdt4240.RawHeroes.independent.Position;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.RenderMode;

import java.util.ArrayList;

/**
 * Created by espen1 on 07.03.2015.
 */
public class AxeUnit extends Unit {

    private final int MAX_HEALTH = 30;

    private final int MIN_DMG = 10;
    private final int MAX_DMG = 15;
    private final int MAX_MOVES = 2;

    private boolean turnedRight;

    private int health;

    private int remainingMoves;

    public AxeUnit(boolean player1Unit) {
        super(player1Unit, 15);
        health = MAX_HEALTH;
        this.remainingMoves = MAX_MOVES;
        this.unitCombatController = new SimpleUnitCombatController(this, MIN_DMG, MAX_DMG, 0, MAX_HEALTH);
        this.unitMoveController = new WalkingUnitMovementController();
        this.weight = 15;
        this.turnedRight = player1Unit;
        System.out.println("Created a axe unit");
    }
    private AxeUnit(boolean player1Unit, int health, boolean hasAttacked, IUnitCombatController unitCombatController, IUnitMovementController unitMoveController, int remainingMoves, int weight) {
        super(player1Unit, health, hasAttacked, unitCombatController, unitMoveController, remainingMoves, weight);
    }

    @Override
    public UnitName getIdentifier() {
        return UnitName.STANDARD_UNIT_2;
    }

    @Override
    public int getHealth(){
        return health;
    }


    @Override
    public int getMaxHealth() {
        return this.unitCombatController.getMaxHealth();
    }




    @Override
    public int[] getAttackDmg() {
        int[] a = new int[2];
        a[0] = this.unitCombatController.getMinAttackDmg();
        a[1] = this.unitCombatController.getMaxAttackDmg();
        return a;
    }

    @Override
    public int getArmor() {
        return this.unitCombatController.getArmor();
    }


    @Override
    public IUnit getCopy() {
        return new AxeUnit(player1Unit, health, hasAttacked, unitCombatController, unitMoveController,remainingMoves, weight);
    }
    @Override
    public int getRemainingMoves() {
        return remainingMoves;
    }
    @Override
    public void setRemainingMoves(int moves){
        remainingMoves = remainingMoves -  moves;
    }

    @Override
    public void resetMoves() {
        remainingMoves = MAX_MOVES;

    }
}

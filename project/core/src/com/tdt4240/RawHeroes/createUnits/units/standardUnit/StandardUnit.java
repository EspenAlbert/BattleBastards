package com.tdt4240.RawHeroes.createUnits.units.standardUnit;


import com.tdt4240.RawHeroes.createUnits.units.Unit;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.IBoardController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitCombatController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitMovementController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.SimpleUnitCombatController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.WalkingUnitMovementController;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;
import com.tdt4240.RawHeroes.independent.Position;

import java.util.ArrayList;

/**
 * Created by espen1 on 07.03.2015.
 */
public class StandardUnit extends Unit {


    private final int MAX_HEALTH = 20;

    private final int MIN_DMG = 5;
    private final int MAX_DMG = 10;


    public StandardUnit(boolean player1Unit) {
        super(player1Unit);
        health = MAX_HEALTH;
        this.remainingMoves = 3;
        this.weight = 5;

        this.unitCombatController = new SimpleUnitCombatController(this, MIN_DMG, MAX_DMG, 1);
        this.unitMoveController = new WalkingUnitMovementController();
        System.out.println("Created a standard unit");
    }
    private StandardUnit(boolean player1Unit, int health, boolean hasAttacked, IUnitCombatController unitCombatController, IUnitMovementController unitMoveController, int remainingMoves, int weight) {
        super(player1Unit, health, hasAttacked, unitCombatController, unitMoveController, remainingMoves, weight);
    }

    @Override
    public UnitName getIdentifier() {
        return UnitName.STANDARD_UNIT;
    }

    @Override
    public IUnit getCopy() {
        return new StandardUnit(player1Unit, health, hasAttacked, unitCombatController, unitMoveController,remainingMoves, weight);
    }
}

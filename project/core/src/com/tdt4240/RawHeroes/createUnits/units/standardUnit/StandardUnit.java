package com.tdt4240.RawHeroes.createUnits.units.standardUnit;

import com.badlogic.gdx.math.Vector2;
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
public class StandardUnit implements IUnit {

    private boolean player1Unit;
    private boolean hasAttacked;
    private IUnitCombatController unitCombatController;
    private IUnitMovementController unitMoveController;

    private int remainingMoves;

    public StandardUnit(boolean player1Unit) {
        this.hasAttacked = false;
        this.player1Unit = player1Unit;

        this.remainingMoves = 3;

        this.unitCombatController = new SimpleUnitCombatController(this, 5, 1);
        this.unitMoveController = new WalkingUnitMovementController();
        System.out.println("Created a standard unit");
    }

    @Override
    public UnitName getIdentifier() {
        return UnitName.STANDARD_UNIT;
    }

    @Override
    public ArrayList<Position> getInflictionZone(Position myPos, Position target) {
        return unitCombatController.getInflictionZone(myPos, target);
    }

    @Override
    public ArrayList<Position> getMovementZone(IBoard board, Position myPos, int movesLeft) {
        return this.unitMoveController.getMovementZone(board, myPos, movesLeft, this.remainingMoves);
    }

    @Override
    public ArrayList<Position> getMovementPath(IBoard board, Position myPos, Position targetPos){
        return this.unitMoveController.getMovementPath(board, myPos, targetPos);
    }

    @Override
    public int inflictDamage(Position myPos, Position targetPos) {
        return unitCombatController.inflictDamage(myPos, targetPos);
    }

    @Override
    public int attacked(int damage) {
        return unitCombatController.attacked(damage); //Final dmg received (after armor etc. reductions)
    }

    @Override
    public void setAttackLogic(IUnitCombatController controller) {
        unitCombatController = controller;
    }

    @Override
    public void setMovementLogic(IUnitMovementController controller) {
        unitMoveController = controller;
    }

    @Override
    public void setHasAttacked() {
        if (hasAttacked){
            this.hasAttacked = false;
        }
        else {
            this.hasAttacked = true;
        }
    }

    @Override
    public boolean isPlayer1Unit() {
        return player1Unit;
    }

    @Override
    public ArrayList<Position> getAttackablePositions(Position pos, int movesLeft, IBoard board) {
        return this.unitCombatController.getAttackablePositions(pos, movesLeft, board);
    }

    @Override
    public int getHealth() {
        //TODO
        return 0;
    }
}

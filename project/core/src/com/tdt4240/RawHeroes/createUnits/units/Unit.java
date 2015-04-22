package com.tdt4240.RawHeroes.createUnits.units;

import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitCombatController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitMovementController;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;
import com.tdt4240.RawHeroes.independent.Position;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by espen1 on 21.04.2015.
 */
public abstract class Unit implements IUnit{

    protected int health;
    protected boolean player1Unit;
    protected boolean hasAttacked;
    protected IUnitCombatController unitCombatController;
    protected IUnitMovementController unitMoveController;

    protected int remainingMoves;
    protected int weight;

    public Unit(boolean player1Unit, int weight) {
        this.hasAttacked = false;
        this.player1Unit = player1Unit;
        this.weight = weight;
    }
    protected Unit(boolean player1Unit, int health, boolean hasAttacked, IUnitCombatController unitCombatController, IUnitMovementController unitMoveController, int remainingMoves, int weight) {
        this.player1Unit = player1Unit;
        this.health = health;
        this.hasAttacked = hasAttacked;
        this.unitCombatController = unitCombatController;
        this.unitMoveController = unitMoveController;
        this.remainingMoves = remainingMoves;
        this.weight = weight;
    }

    @Override
    public abstract UnitName getIdentifier();

    @Override
    public ArrayList<Position> getInflictionZone(Position myPos, Position target) {
        return unitCombatController.getInflictionZone(myPos, target);
    }

    @Override
    public ArrayList<Position> getMovementZone(IBoard board, Position myPos, int energyRemain) {
        return this.unitMoveController.getMovementZone(board, myPos, energyRemain,  board.getCell(myPos).getUnit().getRemainingMoves());
    }

    @Override
    public ArrayList<Position> getMovementPath(IBoard board, Position myPos, Position targetPos){
        return this.unitMoveController.getMovementPath(board, myPos, targetPos);
    }

    @Override
    public int inflictDamage() {
        return unitCombatController.inflictDamage();
    }

    @Override
    public int attacked(int damage) {
        int dmgReceived = unitCombatController.attacked(damage); //Final dmg received (after armor etc. reductions)
        health -= dmgReceived;
        return dmgReceived;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public void setCombatLogic(IUnitCombatController controller) {
        unitCombatController = controller;
    }

    @Override
    public void setMovementLogic(IUnitMovementController controller) {
        unitMoveController = controller;
    }

    @Override
    public void setHasAttacked(boolean value) {
        hasAttacked = value;
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
        return health;
    }

    @Override
    public boolean hasAttacked() {
        return hasAttacked;
    }

    @Override
    public void setRemainingMoves(int moves){
        remainingMoves = remainingMoves -  moves;
    }
}

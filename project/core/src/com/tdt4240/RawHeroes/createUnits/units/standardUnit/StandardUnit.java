package com.tdt4240.RawHeroes.createUnits.units.standardUnit;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitCombatController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitMovementController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.SimpleUnitCombatController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.WalkingUnitMovementController;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;

import java.util.ArrayList;

/**
 * Created by espen1 on 07.03.2015.
 */
public class StandardUnit implements IUnit {

    private boolean player1Unit;
    private boolean hasAttacked;
    private IUnitCombatController unitCombatController;
    private IUnitMovementController unitMoveController;

    public StandardUnit(boolean player1Unit) {
        this.hasAttacked = false;
        this.player1Unit = player1Unit;
        this.unitCombatController = new SimpleUnitCombatController(this, 5, 1);
        this.unitMoveController = new WalkingUnitMovementController();
        System.out.println("Created a standard unit");
    }

    @Override
    public UnitName getIdentifier() {
        return UnitName.STANDARD_UNIT;
    }

    @Override
    public ArrayList<Vector2> getInflictionZone(Vector2 myPos, Vector2 target) {
        return unitCombatController.getInflictionZone(myPos, target);
    }

    @Override
    public ArrayList<Vector2> getMovementZone(Vector2 myPos, int movesLeft) {
        return null;
    }

    @Override
    public int inflictDamage(Vector2 myPos, Vector2 targetPos) {
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
    public ArrayList<Vector2> getAttackablePositions(Vector2 pos, int movesLeft) {
        //TODO
        return null;
    }

    @Override
    public int getHealth() {
        //TODO
        return 0;
    }
}

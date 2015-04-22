package com.tdt4240.RawHeroes.createUnits.factory;

import com.tdt4240.RawHeroes.createUnits.units.axeUnit.AxeUnit;
import com.tdt4240.RawHeroes.createUnits.units.standardUnit.StandardUnit;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitCombatController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitMovementController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.SimpleUnitCombatController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.WalkingUnitMovementController;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;

/**
 * Created by espen1 on 07.03.2015.
 */
public class UnitFactory implements IUnitFactory {

    private static UnitFactory instance;

    public static UnitFactory getInstance() {
        if(instance == null) {
            instance = new UnitFactory();
        }
        return instance;
    }
    private UnitFactory() {

    }

    @Override
    public IUnit createUnit(UnitName name, boolean player1Unit) {
        switch (name) {
            case STANDARD_UNIT:
                return new StandardUnit(player1Unit);
            case UNIT2:
                break;
            case STANDARD_UNIT_2:
                return new AxeUnit(player1Unit);
        }
        return null;
    }

    @Override
    public IUnit createUnitWithLogic(UnitName name, boolean player1Unit, IUnitMovementController movementController, IUnitCombatController combatController) {
        IUnit unit = createUnit(name, player1Unit);
        unit.setCombatLogic(combatController);
        unit.setMovementLogic(movementController);
        return unit;
    }
}

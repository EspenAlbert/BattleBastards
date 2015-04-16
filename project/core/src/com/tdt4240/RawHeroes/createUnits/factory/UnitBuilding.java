package com.tdt4240.RawHeroes.createUnits.factory;

import com.tdt4240.RawHeroes.createUnits.units.standardUnit.StandardUnitFactory;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;

/**
 * Created by espen1 on 07.03.2015.
 */
public class UnitBuilding implements IUnitBuilding {

    private static UnitBuilding instance;
    private final StandardUnitFactory standardUnitFactory;

    public static UnitBuilding getInstance() {
        if(instance == null) {
            instance = new UnitBuilding();
        }
        return instance;
    }
    private UnitBuilding() {
        standardUnitFactory = new StandardUnitFactory();
    }

    @Override
    public IUnit createUnit(UnitName name, boolean player1Unit) {
        switch (name) {
            case STANDARD_UNIT:
                return standardUnitFactory.createUnit(standardUnitFactory.TYPE1, player1Unit);
            case UNIT2:
                break;
            case STANDARD_UNIT_2:
                break;
        }
        return null;
    }
}

package com.tdt4240.RawHeroes.createUnits.units.standardUnit;

import com.tdt4240.RawHeroes.createUnits.factory.IUnitFactory;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;

/**
 * Created by espen1 on 07.03.2015.
 */
public class StandardUnitFactory implements IUnitFactory{
    public final String TYPE1 = "Type1";


    @Override
    public IUnit createUnit(String unitType, boolean player1Unit) {
        if(unitType.equals(TYPE1)) {
            return new StandardUnit(player1Unit);
        }
        else {
            return null;
        }
    }
}

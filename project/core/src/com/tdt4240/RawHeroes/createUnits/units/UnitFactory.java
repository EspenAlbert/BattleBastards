package com.tdt4240.RawHeroes.createUnits.units;

import com.tdt4240.RawHeroes.createUnits.factory.IUnitFactory;
import com.tdt4240.RawHeroes.createUnits.units.axeUnit.AxeUnit;
import com.tdt4240.RawHeroes.createUnits.units.standardUnit.StandardUnit;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;

/**
 * Created by espen1 on 07.03.2015.
 */
public class UnitFactory implements IUnitFactory{


    @Override
    public IUnit createUnit(UnitName unitName, boolean player1Unit) {
        if(unitName == UnitName.STANDARD_UNIT) {
            IUnit unit =  new StandardUnit(player1Unit);
            return unit;
        }else if(unitName == UnitName.STANDARD_UNIT_2) {
            IUnit unit =  new AxeUnit(player1Unit);
            return unit;
        }

        else {
            return null;
        }
    }
}

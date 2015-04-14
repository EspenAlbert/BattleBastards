package com.tdt4240.RawHeroes.createUnits.units.standardUnit;

import com.tdt4240.RawHeroes.createUnits.factory.IUnitFactory;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.SimpleUnitAttackController;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;

/**
 * Created by espen1 on 07.03.2015.
 */
public class StandardUnitFactory implements IUnitFactory{
    public final String TYPE1 = "Type1";
    public final String TYPE2 = "Type2";


    @Override
    public IUnit createUnit(String unitType, boolean player1Unit) {
        if(unitType.equals(TYPE1)) {
            IUnit unit =  new StandardUnit(player1Unit);
            return unit;
            //unit.setAttackLogic(new SimpleUnitAttackController(5));
        }/* else if(unitType.equals(TYPE2)) {

            IUnit unit =  new StandardUnit(player1Unit);
            unit.setAttackLogic(new SimpleUnitAttackController(10));
        }
        */
        else {
            return null;
        }
    }
}

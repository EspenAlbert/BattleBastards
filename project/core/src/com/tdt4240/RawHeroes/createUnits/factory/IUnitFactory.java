package com.tdt4240.RawHeroes.createUnits.factory;

import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;

/**
 * Created by espen1 on 07.03.2015.
 */
public interface IUnitFactory {
    IUnit createUnit(UnitName unitName, boolean player1Unit);
}

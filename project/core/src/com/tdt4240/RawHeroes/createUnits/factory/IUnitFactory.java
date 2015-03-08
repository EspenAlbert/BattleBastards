package com.tdt4240.RawHeroes.createUnits.factory;

import com.tdt4240.RawHeroes.gameLogic.models.IUnit;

/**
 * Created by espen1 on 07.03.2015.
 */
public interface IUnitFactory {
    IUnit createUnit(String unitType, boolean player1Unit);
}

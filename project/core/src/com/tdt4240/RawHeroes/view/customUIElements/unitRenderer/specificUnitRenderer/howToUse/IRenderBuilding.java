package com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse;

import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.gameLogic.models.UnitRenderModel;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface IRenderBuilding {
    IRenderObject getRenderObject(UnitRenderModel renderModel, UnitName name);
}

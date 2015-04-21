package com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse;

import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.renderObjects.AxeUnitRenderObject;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.renderObjects.StandardUnitRenderObject;

/**
 * Created by espen1 on 27.02.2015.
 */
public class RenderBuilding implements IRenderBuilding {
    private static IRenderBuilding instance;

    public static IRenderBuilding getInstance() {
        return instance == null ? new RenderBuilding(): instance;
    }

    @Override
    public IRenderObject getRenderObject(IUnit unit) {
        UnitName unitName = unit.getIdentifier();
        switch (unitName) {
            case STANDARD_UNIT:
                return new StandardUnitRenderObject(unit);
            case STANDARD_UNIT_2:
                return new AxeUnitRenderObject(unit);
        }
        return null;
    }
}

package com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse;

import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.renderObject1.ActualRenderObject;

/**
 * Created by espen1 on 27.02.2015.
 */
public class RenderBuilding implements IRenderBulding {
    private static IRenderBulding instance;

    public static IRenderBulding getInstance() {
        return instance == null ? new RenderBuilding(): instance;
    }

    @Override
    public IRenderObject getRenderObject(IUnit unit) {
        UnitName unitName = unit.getIdentifier();
        switch (unitName) {
            case STANDARD_UNIT:
                return new ActualRenderObject(unit);
            case UNIT2:
                break;
        }
        return null;
    }
}

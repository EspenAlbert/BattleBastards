package com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse;

import com.tdt4240.RawHeroes.gameLogic.models.UnitRenderModel;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.renderObjects.RenderObject;

/**
 * Created by espen1 on 27.02.2015.
 */
public class RenderBuilding implements IRenderBuilding {
    private static IRenderBuilding instance;

    public static IRenderBuilding getInstance() {
        return instance == null ? new RenderBuilding(): instance;
    }

    @Override
    public IRenderObject getRenderObject(UnitRenderModel renderModel, UnitName name) {
        switch (name) {
            case STANDARD_UNIT:
                return new RenderObject(renderModel);
            case STANDARD_UNIT_2:
                return new RenderObject(renderModel);
        }
        return null;
    }
}

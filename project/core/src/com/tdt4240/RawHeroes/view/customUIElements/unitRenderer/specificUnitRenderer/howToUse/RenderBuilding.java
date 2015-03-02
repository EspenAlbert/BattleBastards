package com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse;

import com.tdt4240.RawHeroes.event.move.AttackMove;
import com.tdt4240.RawHeroes.event.move.Move;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;

/**
 * Created by espen1 on 27.02.2015.
 */
public class RenderBuilding implements IRenderBulding {
    private static IRenderBulding instance;

    public static IRenderBulding getInstance() {
        return instance == null ? new RenderBuilding(): instance;
    }

    @Override
    public IRenderObject getStillRender(IUnit unit) {
        UnitName unitName = unit.getIdentifier();
        switch (unitName) {
            case UNIT1:
                break;//Return new Unit1Renderer(pos);
            case UNIT2:
                break;
        }
        return null;
    }

    @Override
    public IRenderObject getAnimationRender(UnitName unitName) {
        return null;
    }

    @Override
    public IRenderObject getRenderForMove(Move move) {
        return null;
    }

    @Override
    public IRenderObject getAnimationAttackRender(AttackMove move) {
        return null;
    }
}

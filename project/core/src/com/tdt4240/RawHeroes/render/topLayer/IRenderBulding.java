package com.tdt4240.RawHeroes.render.topLayer;

import com.tdt4240.RawHeroes.logic.move.AttackMove;
import com.tdt4240.RawHeroes.logic.move.Move;
import com.tdt4240.RawHeroes.logic.unit.IUnit;
import com.tdt4240.RawHeroes.logic.unit.UnitName;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface IRenderBulding {
    IRenderObject getStillRender(IUnit unitName);
    IRenderObject getAnimationRender(UnitName unitName);
    IRenderObject getRenderForMove(Move move);

    IRenderObject getAnimationAttackRender(AttackMove move);
}

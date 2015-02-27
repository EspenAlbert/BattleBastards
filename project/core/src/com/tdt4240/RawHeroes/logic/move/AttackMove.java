package com.tdt4240.RawHeroes.logic.move;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.logic.cell.ICell;
import com.tdt4240.RawHeroes.logic.unit.IUnit;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public class AttackMove extends Move {
    public AttackMove(ICell selectedCell, ICell target) {
        super(selectedCell);

        IUnit unit = selectedCell.getUnit();
        ArrayList<Vector2> inflictionZone = unit.getInflictionZone(selectedCell.getPos(), target.getPos());
    }
}

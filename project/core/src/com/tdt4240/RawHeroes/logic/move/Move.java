package com.tdt4240.RawHeroes.logic.move;

import com.tdt4240.RawHeroes.logic.cell.ICell;

/**
 * Created by espen1 on 27.02.2015.
 */
public abstract class Move {
    private final ICell startCell;
    private int cost;
    public Move(ICell start) {
        this.startCell = start;
    }

    public int getCost() {
        return cost;
    }

    public ICell getStartCell() {
        return startCell;
    }
}

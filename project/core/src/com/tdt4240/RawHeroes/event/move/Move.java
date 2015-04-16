package com.tdt4240.RawHeroes.event.move;

import com.tdt4240.RawHeroes.gameLogic.cell.ICell;

import java.io.Serializable;

/**
 * Created by espen1 on 27.02.2015.
 */
public abstract class Move implements Serializable{
    private final ICell startCell;
    private int cost;
    public Move(ICell start) {
        this.startCell = start;
    }

    public int getCost() {
        return cost;
    }
    public void setCost(int moves){
        cost = moves;
    }

    public ICell getStartCell() {
        return startCell;
    }
}

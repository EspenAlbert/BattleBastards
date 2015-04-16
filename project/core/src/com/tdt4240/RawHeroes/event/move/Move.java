package com.tdt4240.RawHeroes.event.move;

import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

import java.io.Serializable;

/**
 * Created by espen1 on 27.02.2015.
 */
public abstract class Move implements Serializable{
    private final ICell startCell;
    private int cost;
    private final ICell targetCell;

    public Move(ICell start, ICell end) {
        this.startCell = start;
        targetCell = end;
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
    public ICell getTargetCell() {
        return targetCell;
    }

    public abstract void  execute(IBoard board);

    public abstract void undo(IBoard board);
}

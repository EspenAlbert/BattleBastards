package com.tdt4240.RawHeroes.event.move;

import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CellConverter;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.independent.Position;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public abstract class Move implements Serializable{
    private ICell startCell;
    private int cost;
    private ICell targetCell;

    public Move(ICell start, ICell end) {
        this.startCell = start;
        targetCell = end;
    }

    public int getCost() {
        return cost;
    }

    public ICell getStartCell() {
        return startCell;
    }
    public ICell getTargetCell() {
        return targetCell;
    }

    public void setStartCell(ICell cell){
        this.startCell = cell;
    }
    public void setTargetCell(ICell cell){
        this.targetCell = cell;
    }

    public void setCost(int cost){
        this.cost = cost;
    }

    public abstract void  execute(IBoard board);

    public abstract void undo(IBoard board);

    public abstract void convertPositions(CellConverter converter); //Used by server
}

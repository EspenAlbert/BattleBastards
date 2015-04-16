package com.tdt4240.RawHeroes.event.move;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.WalkingUnitMovementController;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.independent.Position;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public class MovementMove extends Move {
    private ArrayList<Position> path;
    private Position target;
    private int length;

    public MovementMove(ICell selectedCell, ICell target, ArrayList<Position> path) {//selectedCell is the startCell
        super(selectedCell, target);
        this.target=target.getPos();
        this.path = path;
        length=path.size();
    }


    public ArrayList<Position> getPath(){
        return path;
    }

    public int getLength(){
        return length;
    }


    public Position getTarget(){
        return target;
    }

    @Override
    public void execute(IBoard board) {
        IUnit mover = getStartCell().getUnit();
        getStartCell().setUnit(null);
        getTargetCell().setUnit(mover);
    }

    @Override
    public void undo(IBoard board) {

    }
}

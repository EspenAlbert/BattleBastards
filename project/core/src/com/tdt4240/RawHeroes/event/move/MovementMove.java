package com.tdt4240.RawHeroes.event.move;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.WalkingUnitMovementController;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public class MovementMove extends Move {
    private ArrayList<Vector2> path;
    private Vector2 target;
    private int cost;

    public MovementMove(ICell selectedCell, ICell target, IBoard board, ArrayList<Vector2> path) {//selectedCell is the startCell
        super(selectedCell);
        this.target=target.getPos();
        this.path = path;
        cost=path.size()-1;
        IUnit mover = selectedCell.getUnit();
        selectedCell.setUnit(null);
        target.setUnit(mover);
    }

    public ArrayList<Vector2> getPath(){
        return path;
    }

    public Vector2 getTarget(){
        return target;
    }

    @Override
    public int getCost(){
        return cost;
    }
}

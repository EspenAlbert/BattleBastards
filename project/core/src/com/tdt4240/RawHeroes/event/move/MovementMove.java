package com.tdt4240.RawHeroes.event.move;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.WalkingUnitMovementController;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public class MovementMove extends Move {
    private ArrayList<Vector2> path;
    private Vector2 start,target;
    private int length;

    public MovementMove(ICell selectedCell, ICell target, IBoard board, ArrayList<Vector2> path) {//selectedCell is the startCell
        super(selectedCell);
        this.target=target.getPos();
        this.path = path;
        length=path.size();
    }

    //TODO lage en calculate path metode. Den må regnes ut uansett

    public ArrayList<Vector2> getPath(){
        return path;
    }

    public int getLength(){
        return length;
    }

    public Vector2 getStart(){
        return start;
    }

    public Vector2 getTarget(){
        return target;
    }
}

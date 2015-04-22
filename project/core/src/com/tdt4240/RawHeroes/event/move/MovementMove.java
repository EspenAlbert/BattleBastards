package com.tdt4240.RawHeroes.event.move;

import com.tdt4240.RawHeroes.createUnits.units.Unit;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CellConverter;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.independent.Position;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by espen1 on 27.02.2015.
 */
public class MovementMove extends Move {
    private ArrayList<Position> path;
    private int length;

    public MovementMove(ICell selectedCell, ICell target, ArrayList<Position> path) {//selectedCell is the startCell
        super(selectedCell, target);
        this.path = path;
        length=path.size();
        this.setCost((length-1) * this.getStartCell().getUnit().getWeight());
        if (getTargetCell().getPos().getX() < getStartCell().getPos().getX() && this.getStartCell().getUnit().isTurnedRight()){
            this.getStartCell().getUnit().turnDirection();
        }else if (getTargetCell().getPos().getX() >= getStartCell().getPos().getX() && !this.getStartCell().getUnit().isTurnedRight()) this.getStartCell().getUnit().turnDirection();
    }


    public ArrayList<Position> getPath(){
        return path;
    }



    @Override
    public void execute(IBoard board) {
        setStartCell(board.getCell(getStartCell().getPos())); //Change so that it is this boards cell
        setTargetCell(board.getCell(getTargetCell().getPos()));
        IUnit mover = getStartCell().getUnit();
        getStartCell().setUnit(null);
        getTargetCell().setUnit(mover);
        getTargetCell().getUnit().setRemainingMoves(length - 1);
    }

    @Override
    public void undo(IBoard board) {
        Collections.reverse(path);
        ICell temp = getTargetCell();
        this.setTargetCell(getStartCell());
        this.setStartCell(temp);
        temp.getUnit().setRemainingMoves(-path.size() + (2 - path.size()));
    }

    @Override
    public void convertPositions(CellConverter converter) {
        int pathLength = path.size();
        ArrayList<Position> newPath = new ArrayList<Position>(pathLength);
        for(Position pos : path) {
            newPath.add(converter.switchPosition(pos));
        }
        getStartCell().setPos(newPath.get(0));
        getTargetCell().setPos(newPath.get(newPath.size() -1));
        path = newPath;
    }
}

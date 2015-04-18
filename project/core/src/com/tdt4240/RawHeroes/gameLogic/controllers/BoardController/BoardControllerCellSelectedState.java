package com.tdt4240.RawHeroes.gameLogic.controllers.boardController;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.event.move.Move;
import com.tdt4240.RawHeroes.event.move.MovementMove;
import com.tdt4240.RawHeroes.gameLogic.cell.CellStatus;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.independent.Position;

import java.util.ArrayList;

/**
 * Created by espen1 on 28.02.2015.
 */
public class BoardControllerCellSelectedState extends BoardControllerState {
    private ICell selectedCell;
    private ArrayList<ICell> walkableCells;
    public BoardControllerCellSelectedState(IBoardController boardController, IBoard board, ICell cell) {
        super(boardController, board);
        selectedCell = cell;
        this.board.switchModeOnCell(selectedCell.getPos(), CellStatus.SELECTED);
        walkableCells = new ArrayList<ICell>();
        addWalkableCells();
    }

    private void addWalkableCells() {
        for (Position coordinates : selectedCell.getUnit().getMovementZone(this.board, selectedCell.getPos(), this.boardController.getRemaining_energy())){
            this.board.switchModeOnCell(coordinates, CellStatus.IN_MOVING_RANGE);
            walkableCells.add(this.board.getCell(coordinates));
        }
    }

    @Override
    public void actionButtonPressed() { //Attack button
        //TODO forandre knappen, må finne ut av hvordan
        this.boardController.setState(new BoardControllerCellAndAttackSelectedState(this.boardController, this.board, this.selectedCell));
    }

    @Override
    public void cellSelected(ICell cell) {
        if (cell.getUnit() != null && cell.getUnit().isPlayer1Unit() == boardController.iAmPlayer1() ){ //Velge ny unit
            this.board.switchModeOnCell(selectedCell.getPos(), CellStatus.DEFAULT);
            selectedCell = cell;
            popped();
            addWalkableCells();
            this.board.switchModeOnCell(selectedCell.getPos(), CellStatus.SELECTED);
        }
        else if(cell.getStatus()== CellStatus.IN_MOVING_RANGE){ //Bevege valgt unit til ny celle
            //TODO sjekke om man har nok energi før movet gjøres
            // Vi burde ha en limit på hvor mange ganger man kan flytte en unit også
            ArrayList<Position> path = selectedCell.getUnit().getMovementPath(this.board, selectedCell.getPos(), cell.getPos());//TODO: Fix movement path
            this.boardController.addMove(new MovementMove(selectedCell, cell, path));
            this.board.switchModeOnCell(selectedCell.getPos(), CellStatus.DEFAULT);
            selectedCell = cell;
            popped();
            addWalkableCells();
            this.board.switchModeOnCell(selectedCell.getPos(), CellStatus.SELECTED);
        }
        else if(cell.getStatus()== CellStatus.DEFAULT){
            this.board.switchModeOnCell(selectedCell.getPos(), CellStatus.DEFAULT);
            this.boardController.setState(new BoardControllerNoCellSelectedState(this.boardController, this.board));
        }
    }

    @Override
    public BoardControllerStateEvent getEvent() {
        //TODO finne en bedre måte å gjøre energy parameteren på
        return new BoardControllerStateEvent(0, "Attack");
    }

    @Override
    public void popped() {
        for (ICell cell : walkableCells){
            if (cell.getStatus() == CellStatus.IN_MOVING_RANGE)this.board.switchModeOnCell(cell.getPos(), CellStatus.DEFAULT);
        }
    }
}

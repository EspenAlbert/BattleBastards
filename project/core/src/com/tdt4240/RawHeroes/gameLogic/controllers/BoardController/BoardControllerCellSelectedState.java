package com.tdt4240.RawHeroes.gameLogic.controllers.boardController;

import com.tdt4240.RawHeroes.event.move.Move;
import com.tdt4240.RawHeroes.event.move.MovementMove;
import com.tdt4240.RawHeroes.gameLogic.cell.CellStatus;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

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
        selectedCell.setStatus(CellStatus.SELECTED);
        walkableCells = new ArrayList<ICell>();
        //TODO sette IN_MOVING_RANGE status på celler som er innenfor moving rangen til en unit, f.eks.:
        /*for (int x = selectedCell.getPos.getX()-selectedCell.getUnit().getWalkDist(); x <= selectedCell.getPos.getX()+selectedCell.getUnit().getWalkDist(); x++{
            for (int y = selectedCell.getPos.getY()-selectedCell.getUnit().getWalkDist(); y <= selectedCell.getPos.getY()+selectedCell.getUnit().getWalkDist(); y++{
                if (this.board.getCells()[x][y].getUnit() == null)this.board.getCells()[x][y].setStatus(CellStatus.IN_MOVING_RANGE)
            }
        }
        */
    }

    @Override
    public void actionButtonPressed() { //Attack button
        //TODO forandre knappen, må finne ut av hvordan
        this.boardController.setState(new BoardControllerCellAndAttackSelectedState(this.boardController, this.board, this.selectedCell));
    }

    @Override
    public void cellSelected(ICell cell) {
        if (cell.getStatus() == CellStatus.SELECTABLE){ //Velge ny unit
            selectedCell.setStatus(CellStatus.SELECTABLE);
            selectedCell = cell;
            selectedCell.setStatus(CellStatus.SELECTED);
        }
        else if(cell.getStatus()== CellStatus.IN_MOVING_RANGE){ //Bevege valgt unit til ny celle
            //TODO sjekke om man har nok energi før movet gjøres
            // Vi burde ha en limit på hvor mange ganger man kan flytte en unit også
            this.boardController.addMove(new MovementMove(selectedCell, cell));
            //TODO endre på hvilken cell som er selected etter move, gjøres her eller i move?
        }
        else if(cell.getStatus()== CellStatus.DEFAULT){
            selectedCell.setStatus(CellStatus.SELECTABLE);
            this.boardController.setState(new BoardControllerNoCellSelectedState(this.boardController, this.board));
        }
    }

    @Override
    public void popped() {
        for (ICell cell : walkableCells){
            cell.setStatus(CellStatus.DEFAULT);
        }
    }
}

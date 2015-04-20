package com.tdt4240.RawHeroes.gameLogic.controllers.boardController;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.event.move.AttackMove;
import com.tdt4240.RawHeroes.gameLogic.cell.CellStatus;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.independent.Position;

import java.util.ArrayList;

/**
 * Created by espen1 on 28.02.2015.
 */
public class BoardControllerCellAndAttackSelectedState extends BoardControllerState {
    private ICell selectedCell;
    private ArrayList<ICell> attackableCells;
    public BoardControllerCellAndAttackSelectedState(IBoardController boardController, IBoard board, ICell cell) {
        super(boardController, board);
        selectedCell = cell;
        this.board.switchModeOnCell(selectedCell.getPos(), CellStatus.SELECTED);
        attackableCells = new ArrayList<ICell>();
        for (Position coordinates : selectedCell.getUnit().getAttackablePositions(selectedCell.getPos(), this.boardController.getRemaining_energy(), this.board)){
            this.board.switchModeOnCell(coordinates, CellStatus.ATTACKABLE);
            attackableCells.add(this.board.getCell(coordinates));
        }
    }

    @Override
    public void actionButtonPressed() {
        this.boardController.setState(new BoardControllerCellSelectedState(this.boardController, this.board, this.selectedCell));
    }

    @Override
    public void cellSelected(ICell cell) {
        if (cell.getStatus() == CellStatus.ATTACKABLE){
            AttackMove move = new AttackMove(selectedCell, cell);
            if (move.getCost() <= this.boardController.getRemaining_energy())this.boardController.addMove(move);
            //TODO disable så samme unit ikke kan angripe flere ganger per tur
            this.board.switchModeOnCell(selectedCell.getPos(), CellStatus.DEFAULT);
            this.boardController.setState(new BoardControllerNoCellSelectedState(this.boardController, this.board));
        }

    }

    @Override
    public BoardControllerStateEvent getEvent() {
        //TODO finne en bedre måte å gjøre energy parameteren på
        return new BoardControllerStateEvent(0, "Move");
    }

    @Override
    public void popped() {
        for (ICell cell : attackableCells){
            if(cell.getStatus() == CellStatus.ATTACKABLE)this.board.switchModeOnCell(cell.getPos(), CellStatus.DEFAULT);
        }
    }
}

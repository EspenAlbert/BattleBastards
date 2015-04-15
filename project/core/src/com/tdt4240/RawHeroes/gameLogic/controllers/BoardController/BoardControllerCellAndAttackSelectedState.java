package com.tdt4240.RawHeroes.gameLogic.controllers.boardController;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.event.move.AttackMove;
import com.tdt4240.RawHeroes.gameLogic.cell.CellStatus;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

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
        selectedCell.setStatus(CellStatus.SELECTED);
        attackableCells = new ArrayList<ICell>();
        for (Vector2 coordinates : selectedCell.getUnit().getAttackablePositions(selectedCell.getPos(), this.boardController.getRemaining_energy())){
            this.board.getCell(coordinates).setStatus(CellStatus.ATTACKABLE);
            attackableCells.add(this.board.getCell(coordinates));
        }
    }

    @Override
    public void actionButtonPressed() {
        //TODO forandre knappen, må finne ut av hvordan
        this.boardController.setState(new BoardControllerCellSelectedState(this.boardController, this.board, this.selectedCell));
    }

    @Override
    public void cellSelected(ICell cell) {
        if (cell.getStatus() == CellStatus.ATTACKABLE){
            AttackMove move = new AttackMove(selectedCell, cell);
            if (move.getCost() <= this.boardController.getRemaining_energy())this.boardController.addMove(new AttackMove(selectedCell, cell));
            //TODO disable så samme unit ikke kan angripe flere ganger per tur
        }
        //TODO også ha tilbakegåing til NoCellSelectedState hvis man trykker på en default

    }

    @Override
    public void popped() {
        for (ICell cell : attackableCells){
            cell.setStatus(CellStatus.DEFAULT);
        }
    }
}

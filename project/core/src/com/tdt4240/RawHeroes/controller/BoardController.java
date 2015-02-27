package com.tdt4240.RawHeroes.controller;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.logic.cell.CellStatus;
import com.tdt4240.RawHeroes.logic.cell.ICell;
import com.tdt4240.RawHeroes.logic.model.IBoard;
import com.tdt4240.RawHeroes.logic.move.AttackMove;
import com.tdt4240.RawHeroes.logic.move.Move;
import com.tdt4240.RawHeroes.logic.move.MovementMove;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public class BoardController implements IBoardController {

    private final IBoard board;
    private final IBoardMover boardMover;

    private boolean unitSelected;
    private boolean attackMode;

    private ICell selectedCell;
    private int movesLeft;


    public BoardController(IBoard board, IBoardMover boardMover, int movesLeft) {
        this.board = board;
        this.boardMover = boardMover;
        this.movesLeft = movesLeft;
    }


    @Override
    public void cellTouched(Vector2 coordinates) {
        if (unitSelected && !attackMode) {
            CellStatus selectedCellStatus = board.getCell(coordinates).getStatus();
            if (selectedCellStatus.equals(CellStatus.MOVEABLE)) {
                moveSelectedPieceTo(coordinates);
            } else if (selectedCellStatus.equals(CellStatus.SELECTABLE)) {
                selectPiece(coordinates);
            } else {
                throw new IllegalArgumentException("The touched cell have wrong status!");
            }
        } else if (!unitSelected) {
            selectPiece(coordinates);
        } else if (attackMode) {
            CellStatus selectedCellStatus = board.getCell(coordinates).getStatus();
            if (selectedCellStatus.equals(CellStatus.ATTACKABLE)) {
                attackPiece(coordinates);
            } else {
                selectPiece(coordinates);
            }
        } else {
            throw new IllegalArgumentException("Tried to touch a cell and the state was unknown");
        }
    }

    @Override
    public void attackButtonTouched() {
        //TODO: Implement logic
    }
    public void selectPiece(Vector2 coordinate) {
        if (selectedCell.getUnit() != null) {
            deSelectPiece();
            selectedCell = board.getCell(coordinate);
            board.switchModeOnCell(CellStatus.SELECTED);
            switchCellsToMoveable();
        } else {
            deSelectPiece();
            attackMode = false;
        }
    }


    @Override
    public void deSelectPiece() {
        //TODO: Not complete
        if(selectedCell == null) return;
        if(attackMode) {
            disableAttack();
            return;
        }

    }

    private void disableAttack() {
        ArrayList<Vector2> attackableCells = selectedCell.getUnit().getAttackablePosisions(selectedCell.getPos(), movesLeft);
        for (Vector2 attackableCell : attackableCells) {
            if(board.getCell(attackableCell) != null) {
                board.switchModeOnCell(CellStatus.DEFAULT);
            }
        }
        attackMode = false;
    }



    private void switchCellsToMoveable() {
        ArrayList<Vector2> moveableCells = selectedCell.getUnit().getMovementZone(selectedCell.getPos(), movesLeft);
        for (Vector2 moveableCell : moveableCells) {
            if(board.getCell(moveableCell) != null && board.getCell(moveableCell).getUnit() == null) { //The coordinate is one the board and have no unit in it.
                board.switchModeOnCell(CellStatus.MOVEABLE);
            }
        }
    }

    private void addMove(Move move) {
        movesLeft = movesLeft - move.getCost();

    }
    private void attackPiece(Vector2 coordinates) {
        addMove(new AttackMove(selectedCell, board.getCell(coordinates)));
    }


    private void moveSelectedPieceTo(Vector2 coordinates) {
        addMove(new MovementMove(selectedCell, board.getCell(coordinates)));
    }
}

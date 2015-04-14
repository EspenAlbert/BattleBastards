package com.tdt4240.RawHeroes.gameLogic.controllers.boardController;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.event.move.Move;

import java.util.Stack;

/**
 * Created by espen1 on 27.02.2015.
 */
public class BoardController implements IBoardController {

    private final IBoard board;
    private final IBoardMover boardMover;
    private Stack<BoardControllerState> boardStates;

    private int remaining_energy;


    public BoardController(IBoard board, IBoardMover boardMover, int remaining_energy) {
        this.board = board;
        this.boardMover = boardMover;
        this.remaining_energy = remaining_energy;
        this.boardStates.push(new BoardControllerNoCellSelectedState(this, this.board));
    }

    public void setState(BoardControllerState state) {
        boardStates.pop().popped();
        boardStates.push(state);
    }

    public void addMove(Move move) {
        remaining_energy = remaining_energy - move.getCost();
        boardMover.add(move);
    }

    @Override
    public void cellTouched(Vector2 coordinates) {
        boardStates.peek().cellSelected(board.getCell(coordinates));
        //TODO: Implement logic in the states
        /*
        if (unitSelected && !attackMode) {
            CellStatus selectedCellStatus = board.getCell(coordinates).getStatus();
            if (selectedCellStatus.equals(CellStatus.IN_MOVING_RANGE)) {
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
        */
    }

    @Override
    public void actionButtonTouched() {
        boardStates.peek().actionButtonPressed();
        //TODO: Implement logic
    }

    @Override
    public void cellTouchedLong(Vector2 coordinates){

    }

    //TODO: Move the rest of the function to the corresponding states
    public void selectPiece(Vector2 coordinate) {
        /*
        if (selectedCell.getUnit() != null) {
            deSelectPiece();
            selectedCell = board.getCell(coordinate);
            board.switchModeOnCell(CellStatus.SELECTED);
            switchCellsToMoveable();
        } else {
            deSelectPiece();
            attackMode = false;
        }
        */
    }


    public void deSelectPiece() {
        //TODO: Not complete
        /*
        if(selectedCell == null) return;
        if(attackMode) {
            disableAttack();
            return;
        }
        */

    }

    private void disableAttack() {
        /*
        ArrayList<Vector2> attackableCells = selectedCell.getUnit().getAttackablePositions(selectedCell.getPos(), movesLeft);
        for (Vector2 attackableCell : attackableCells) {
            if(board.getCell(attackableCell) != null) {
                board.switchModeOnCell(CellStatus.DEFAULT);
            }
        }
        attackMode = false;
        */
    }


    private void switchCellsToMoveable() {
        /*
        ArrayList<Vector2> moveableCells = selectedCell.getUnit().getMovementZone(selectedCell.getPos(), movesLeft);
        for (Vector2 moveableCell : moveableCells) {
            if(board.getCell(moveableCell) != null && board.getCell(moveableCell).getUnit() == null) { //The coordinate is one the board and have no unit in it.
                board.switchModeOnCell(CellStatus.IN_MOVING_RANGE);
            }
        }
        */
    }


    private void attackPiece(Vector2 coordinates) {
        //addMove(new AttackMove(selectedCell, board.getCell(coordinates)));
    }


    private void moveSelectedPieceTo(Vector2 coordinates) {
        // addMove(new MovementMove(selectedCell, board.getCell(coordinates)));
    }
}

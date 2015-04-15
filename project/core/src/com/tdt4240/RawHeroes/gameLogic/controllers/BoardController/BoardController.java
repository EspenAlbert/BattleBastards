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
        //TODO sette riktig state. Kanskje Replay hvis moveslist ikke er tom eller noe
    }

    public void setState(BoardControllerState state) {
        boardStates.pop().popped();
        boardStates.push(state);
    }

    public void addMove(Move move) {
        remaining_energy = remaining_energy - move.getCost();
        boardMover.add(move);
    }

    public void undoMove(){
        this.boardMover.undo();
    }

    @Override
    public void cellTouched(Vector2 coordinates) {
        boardStates.peek().cellSelected(board.getCell(coordinates));
    }

    @Override
    public void actionButtonTouched() {
        boardStates.peek().actionButtonPressed();
    }

    @Override
    public void cellTouchedLong(Vector2 coordinates){
        if (board.getCell(coordinates).getUnit() != null){
            //TODO åpne et nytt vindu med informasjon om denne uniten
        }
    }

    public int getRemaining_energy(){
        return this.remaining_energy;
    }
}

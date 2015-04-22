package com.tdt4240.RawHeroes.gameLogic.controllers.boardController;

import com.tdt4240.RawHeroes.event.listener.IMoveListener;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.event.move.Move;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.independent.Position;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by espen1 on 27.02.2015.
 */
public class BoardController implements IBoardController {

    private final IBoard board;
    private final IBoardMover boardMover;
    private Stack<BoardControllerState> boardStates;

    private ArrayList<BoardControllerStateListener> listeners;

    private int remaining_energy;
    private boolean iAmPlayer1;


    public BoardController(IBoard board, int remaining_energy, boolean iAmPlayer1) {
        this.board = board;
        if(!iAmPlayer1) {
            board.convertCellsToOtherPlayer();
        }
        boardMover = new BoardMover(board);
        this.remaining_energy = remaining_energy;
        this.iAmPlayer1 = iAmPlayer1;
        boardStates = new Stack<BoardControllerState>();
        listeners = new ArrayList<BoardControllerStateListener>();
        boardStates.push(new BoardControllerReplayState(this, this.board));
        fireStateChanged(boardStates.peek().getEvent());
    }

    public void setState(BoardControllerState state) {
        boardStates.pop().popped();
        boardStates.push(state);
        fireStateChanged(state.getEvent());
    }
    private void refreshState(){
        fireStateChanged(boardStates.peek().getEvent());
    }

    private void fireStateChanged(BoardControllerStateEvent event) {
        for (BoardControllerStateListener listener : listeners){
            listener.stateChanged(event);
        }
    }

    public void addMove(Move move) {
        remaining_energy = remaining_energy - move.getEnergyCost();
        boardMover.add(move);
    }

    public void undoMove(){
        Move move = boardMover.undo();
        if(move != null){
            remaining_energy += move.getEnergyCost();
        }
    }

    @Override
    public void cellTouched(Position coordinates) {
        if(coordinates.getY() > board.getHeight()-1 || coordinates.getX() > board.getWidth()-1) return;
        boardStates.peek().cellSelected(board.getCell(coordinates));
    }

    @Override
    public void actionButtonTouched() {
        boardStates.peek().actionButtonPressed();
    }

    @Override
    public void cellTouchedLong(Position coordinates){
        if (board.getCell(coordinates).getUnit() != null){
            //TODO åpne et nytt vindu med informasjon om denne uniten
        }
    }

    public int getRemaining_energy(){
        return this.remaining_energy;
    }

    public void addBoardControllerStateListener(BoardControllerStateListener listener){
        this.listeners.add(listener);
        this.refreshState();
    }

    public boolean iAmPlayer1() {
        return iAmPlayer1;
    }

    public static void resetUnitAttacks(IBoard board) {
        ArrayList<Position> unitPositions = board.getUnitPositions();
        for(Position unitPosition : unitPositions) {
            board.getCell(unitPosition).getUnit().setHasAttacked(false);
            board.getCell(unitPosition).getUnit().resetMoves();
        }
    }

    @Override
    public void executeMovesFromOtherPlayer(ArrayList<Move> lastMoves, boolean iAmPlayer1) {
        boardMover.executeMovesFromOtherPlayer(lastMoves, iAmPlayer1);
    }

    @Override
    public ArrayList<Move> confirmMoves() {
        return boardMover.confirmMoves();
    }

    @Override
    public void addMoveListener(IMoveListener listener) {
        boardMover.addMoveListener(listener);

    }
}

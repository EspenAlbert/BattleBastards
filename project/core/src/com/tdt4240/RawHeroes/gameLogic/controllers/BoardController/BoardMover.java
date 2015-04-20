package com.tdt4240.RawHeroes.gameLogic.controllers.boardController;

import com.tdt4240.RawHeroes.event.move.AttackMove;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CellConverter;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.event.listener.IMoveListener;
import com.tdt4240.RawHeroes.event.move.Move;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public class BoardMover implements IBoardMover {

    private ArrayList<Move> moves;
    private IBoard board;
    private ICell[][] initialBoard;
    private ArrayList<IMoveListener> listeners;

    public BoardMover(IBoard board) {
        this.board = board;
        moves = new ArrayList<Move>();
        listeners = new ArrayList<IMoveListener>();

    }
    @Override
    public void addMoveListener(IMoveListener listener) {
        listeners.add(listener);
    }

    @Override
    public ArrayList<Move> confirmMoves() {
        executeMovesFromBeginning();
        return moves;
    }

    private void executeMovesFromBeginning() {
        board.changeCells(initialBoard);
        for(Move move: moves) {
            move.execute(board);
            if(move instanceof AttackMove) {
                move.execute(board);
            }
            alertListeners(move);
        }
    }

    @Override
    public void add(Move move) {
        moves.add(move);
        doMove(move);
    }

    private void doMove(Move move) {
        move.execute(board);
        alertListeners(move);
    }

    private void alertListeners(Move move) {
        for(IMoveListener listener: listeners) {
            listener.moveExecuted(move);
        }
    }

    @Override
    public Move undo() {
        if(moves.size() > 0) {
            moves.get(moves.size()-1).undo(board);
            doMove(moves.get(moves.size()-1));
            return moves.remove(moves.size() -1);
        }
        return null;
    }



    ///Method that are called when a new ActiveGameScreen is initialized:
    @Override
    public void executeMovesFromOtherPlayer(ArrayList<Move> lastMoves, boolean iAmPlayer1) {
        BoardController.resetUnitAttacks(board);
        if(lastMoves == null) lastMoves = new ArrayList<Move>();
        CellConverter converter = null;
        if(!iAmPlayer1) converter = new CellConverter(board.getWidth(), board.getHeight());
        for(Move move : lastMoves) {
            if(!iAmPlayer1) move.convertPositions(converter);
            if(move instanceof AttackMove) {
                move.execute(board);
            }
            doMove(move);

        }
        BoardController.resetUnitAttacks(board);
        initialBoard = board.deepCopy();
    }
}

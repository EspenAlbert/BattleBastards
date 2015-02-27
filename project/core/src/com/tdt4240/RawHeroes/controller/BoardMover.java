package com.tdt4240.RawHeroes.controller;

import com.tdt4240.RawHeroes.logic.model.IBoard;
import com.tdt4240.RawHeroes.logic.move.IMoveListener;
import com.tdt4240.RawHeroes.logic.move.Move;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public class BoardMover implements IBoardMover {

    private ArrayList<Move> moves;
    private final IBoard board;
    private ArrayList<IMoveListener> listeners;

    public BoardMover(IBoard board) {
        this.board = board;
        moves = new ArrayList<>();

    }
    @Override
    public void addMoveListener(IMoveListener listener) {
        listeners.add(listener);
    }
    @Override
    public void add(Move move) {
        moves.add(move);
        doMove(move);
    }

    private void doMove(Move move) {
        //TODO: Implement move logic
        for(IMoveListener listener: listeners) {
            listener.moveExecuted(move);
        }
    }

    @Override
    public void undo() {
        if(moves.size() > 0) {
            //TODO: Implement undo move
        }
    }



    @Override
    public void executeMoves(ArrayList<Move> lastMoves) {

    }
}

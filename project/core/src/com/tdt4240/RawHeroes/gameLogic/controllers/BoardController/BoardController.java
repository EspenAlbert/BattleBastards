package com.tdt4240.RawHeroes.gameLogic.controllers.boardController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.event.move.Move;
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
    private String actionButtonText = "Action";


    public BoardController(IBoard board, IBoardMover boardMover, int remaining_energy, boolean iAmPlayer1) {
        this.board = board;
        this.boardMover = boardMover;
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
        remaining_energy = remaining_energy - move.getCost();
        boardMover.add(move);
    }

    public void undoMove(){
        Move move = this.boardMover.undo();
        //TODO FIX
        if(move != null) remaining_energy += move.getCost();
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
}

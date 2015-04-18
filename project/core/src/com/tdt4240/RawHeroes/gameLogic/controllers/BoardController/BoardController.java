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
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.independent.GameConstants;

import java.util.Stack;

/**
 * Created by espen1 on 27.02.2015.
 */
public class BoardController implements IBoardController {

    private final IBoard board;
    private final IBoardMover boardMover;
    private Stack<BoardControllerState> boardStates;
    private Dialog createUnitDetailsScreenDialog;

    private int remaining_energy;


    public BoardController(IBoard board, IBoardMover boardMover, int remaining_energy) {
        createUnitDetailsScreenDialog = null;
        this.board = board;
        this.boardMover = boardMover;
        this.remaining_energy = remaining_energy;
        boardStates = new Stack<BoardControllerState>();
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
            //createUnitDetailsScreen(board.getCell(coordinates).getUnit());
        }
    }

    public int getRemaining_energy(){
        return this.remaining_energy;
    }
}

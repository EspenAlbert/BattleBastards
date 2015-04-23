package com.tdt4240.RawHeroes.gameLogic.controllers.boardController;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.tdt4240.RawHeroes.event.listener.IMoveListener;
import com.tdt4240.RawHeroes.event.move.Move;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.independent.Position;
import com.tdt4240.RawHeroes.view.topLayer.GameView;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface IBoardController {

    void cellTouched(Position coordinates);
    void cellTouchedLong(Position coordinates);
    void actionButtonTouched();
    void addMove(Move move);
    void undoMove();
    void setState(BoardControllerState state);
    public void addBoardControllerStateListener(BoardControllerStateListener listener);
    int getRemaining_energy();
    boolean iAmPlayer1();
    void executeMovesFromOtherPlayer(ArrayList<Move> lastMoves, boolean iAmPlayer1);

    ArrayList<Move> confirmMoves();

    void addMoveListener(IMoveListener listener);
}

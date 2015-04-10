package com.tdt4240.RawHeroes.gameLogic.inputListeners;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.IBoardController;
import com.tdt4240.RawHeroes.gameLogic.models.ICamera;
import com.tdt4240.RawHeroes.independent.inputListeners.TouchDown;

/**
 * Created by espen1 on 27.02.2015.
 */
public class TouchListenerBoard implements TouchDown {

    private final ICamera boardView;
    private final IBoardController boardController;

    public TouchListenerBoard(IBoardController boardController, ICamera boardView) {
        this.boardController = boardController;
        this.boardView = boardView;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 touchPoint = new Vector2(screenX, screenY);
        try {
            Vector2 touchedCell = boardView.convertPixelCoordinateToCell(touchPoint);
            boardController.cellTouched(touchedCell);
        } catch (IllegalArgumentException exception) {
            System.out.println("Touched something outside the board...");
        }
        return false;
    }
}

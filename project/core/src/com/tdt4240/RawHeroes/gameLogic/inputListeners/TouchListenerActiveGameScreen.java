package com.tdt4240.RawHeroes.gameLogic.inputListeners;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.IBoardController;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.ICameraController;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.independent.Position;
import com.tdt4240.RawHeroes.independent.inputListeners.TouchDown;
import com.tdt4240.RawHeroes.topLayer.screens.ActiveGameScreen;

/**
 * Created by espen1 on 27.02.2015.
 */
public class TouchListenerActiveGameScreen implements TouchDown {

    private final ICameraController cameraController;
    private ActiveGameScreen gameScreen;
    private final IBoardController boardController;
    private boolean firstTouch;
    private static float boardMaxXCoordinate;
    private float boardMinXCoordinate;

    public TouchListenerActiveGameScreen(IBoardController boardController, ICameraController cameraController, ActiveGameScreen gameScreen) {
        this.boardController = boardController;
        this.cameraController = cameraController;
        this.gameScreen = gameScreen;
        firstTouch = true;
    }

    @Override
    public boolean touchDown(float screenX, float screenY, int pointer, int button) {
        if(firstTouch) {
            boardMinXCoordinate = cameraController.getScreenPixelCoordinate(0, 0).x;
            boardMaxXCoordinate = cameraController.getScreenPixelCoordinate(GameConstants.GAME_VISIBLE_WIDTH, 0).x;
            firstTouch = false;
        }
        if(screenX > boardMaxXCoordinate) {
            screenY = Math.abs((screenY - GameConstants.RESOLUTION_HEIGHT) % GameConstants.RESOLUTION_HEIGHT);
            //Converts the coordinate so that the y-coordinate is 0 when the bottom of the screen is touched...

            hudTouch(screenY);
            //TODO
            System.out.println("Action panel touch" + " y coordinate: " + screenY);
            return false;
        }
        else if(screenX < boardMinXCoordinate) {
            System.out.println("Touch outside of board");
            return false;//TODO: A invalid area was touched...
        }
        else {
            Position touchedCell = cameraController.convertPixelCoordinateToCell(new Vector2(screenX, screenY));
            System.out.println("Cell touched: " + touchedCell.getX() + "," + touchedCell.getY());
            boardController.cellTouched(touchedCell);
            //gameScreen.cellClicked(touchedCell);

        }
        return false;
    }

    private void hudTouch(float screenY) {
        int buttonHeight = GameConstants.RESOLUTION_HEIGHT /4;
        //TODO: If screenY  is within action button y
        if (screenY > 0 && screenY < 1*buttonHeight){
            System.out.println("Quit");
            this.gameScreen.finish("Quit");
        }
        else if (screenY > 1*buttonHeight && screenY < 2*buttonHeight){
            System.out.println("Action");
            this.boardController.actionButtonTouched();
        }
        else if (screenY > 2*buttonHeight && screenY < 3*buttonHeight){
            System.out.println("Send");
            gameScreen.confirmTurn();
            //TODO send
        }
        /*if(screenY > (GameConstants.RESOLUTION_HEIGHT / 2)) {
            cameraController.translate(0, 1);
        }
        else {
            cameraController.translate(0, -1);
        }*/
    }

    public static float getBoardMaxXCoordinate() {
        return boardMaxXCoordinate;
    }
}

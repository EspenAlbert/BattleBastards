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
            return false;
        }
        else if(screenX < boardMinXCoordinate) {
            return false;
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
        if (screenY > 0 && screenY < 1*buttonHeight){
<<<<<<< HEAD
<<<<<<< HEAD
            this.gameScreen.backToMainMenu();
=======
            System.out.println("Quit");
            this.gameScreen.finish("Quit");
>>>>>>> Temporary commit for rebase
=======
            System.out.println("Quit");
            this.gameScreen.finish("Quit");
>>>>>>> d4198269fe2dc6378ea032dee10b3dbb2fd964d4
        }
        else if (screenY > 1*buttonHeight && screenY < 2*buttonHeight){
            this.boardController.actionButtonTouched();
        }
        else if (screenY > 2*buttonHeight && screenY < 3*buttonHeight){
            gameScreen.confirmTurn();
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

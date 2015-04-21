package com.tdt4240.RawHeroes.gameLogic.inputListeners;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CameraController;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.independent.MyInputProcessor;
import com.tdt4240.RawHeroes.independent.Position;
import com.tdt4240.RawHeroes.independent.inputListeners.TouchDown;
import com.tdt4240.RawHeroes.topLayer.screens.ActiveGameScreen;
import com.tdt4240.RawHeroes.topLayer.screens.ScreenStateManager;
import com.tdt4240.RawHeroes.view.customUIElements.finishScreenRenderer.FinishScreenRenderer;
import com.tdt4240.RawHeroes.view.topLayer.GameView;

/**
 * Created by espen1 on 20.04.2015.
 */
public class QuitGameTouchDown implements TouchDown {
    private final String message;
    private ActiveGameScreen activeGameScreen;
    private CameraController cameraController;

    public QuitGameTouchDown(String message, CameraController cameraController, ActiveGameScreen activeGameScreen) {
        this.message = message;
        this.cameraController = cameraController;
        this.activeGameScreen = activeGameScreen;
    }

    @Override
    public boolean touchDown(float screenX, float screenY, int pointer, int button) {
        Position coordinates = cameraController.convertPixelCoordinateToCell(new Vector2(screenX, screenY));
        System.out.println(coordinates);
        System.out.println(message);
        if(FinishScreenRenderer.isNoOptionMessage(message)) {
            System.out.println("No option");
            if(coordinates.getY() < GameConstants.GAME_VISIBLE_HEIGHT-1 && coordinates.getX() < GameConstants.GAME_VISIBLE_WIDTH) activeGameScreen.backToMainMenu();
        }
        if(coordinates.getY() < GameConstants.GAME_VISIBLE_HEIGHT-1 && coordinates.getX() < GameConstants.GAME_VISIBLE_WIDTH) {
            System.out.println("options..");
            if(coordinates.getX() < (GameConstants.GAME_VISIBLE_WIDTH/ 2)) {
                activeGameScreen.backToMainMenu();
            }
            else {
                activeGameScreen.abortFinish();
            }
        }
        return false;
    }
}

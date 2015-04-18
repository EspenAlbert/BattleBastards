package com.tdt4240.RawHeroes.gameLogic.inputListeners;

import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CameraController;
import com.tdt4240.RawHeroes.independent.inputListeners.IFlingListener;

/**
 * Created by espen1 on 17.04.2015.
 */
public class TranslateCamera implements IFlingListener {

    private final CameraController camera;

    public TranslateCamera(CameraController controller) {
        camera = controller;
    }
    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        int dy = velocityY > 0 ? 1 : -1;
        camera.translate(0, dy);
        return false;
    }
}

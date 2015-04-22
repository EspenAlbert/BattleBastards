package com.tdt4240.RawHeroes.gameLogic.inputListeners;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CameraController;
import com.tdt4240.RawHeroes.independent.inputListeners.IPanListener;
import com.tdt4240.RawHeroes.independent.inputListeners.IPanStopListener;

/**
 * Created by espen1 on 17.04.2015.
 */
public class EventToTranslateCamera implements IPanListener, IPanStopListener {

    private final CameraController camera;
    private boolean active;
    private Vector2 startPos;

    public EventToTranslateCamera(CameraController controller) {
        camera = controller;
        active = false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if(!active) {
            startPos = new Vector2(x, y);
            active = true;
        }
        return false;
    }
    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        if (startPos == null) return false;
        Vector2 endPos = new Vector2(x, y);
        Vector2 movement = endPos.sub(startPos);
        int translateY = Math.round(movement.y /50);
        active = false;
        camera.translate(0, translateY);
        return false;
    }

}

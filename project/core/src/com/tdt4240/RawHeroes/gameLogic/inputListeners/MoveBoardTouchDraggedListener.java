package com.tdt4240.RawHeroes.gameLogic.inputListeners;

import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CameraController;
import com.tdt4240.RawHeroes.independent.inputListeners.ITouchDragged;

/**
 * Created by espen1 on 17.04.2015.
 */
public class MoveBoardTouchDraggedListener implements ITouchDragged {

    private final CameraController camera;

    public MoveBoardTouchDraggedListener(CameraController controller) {
        camera = controller;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println("touched dragged event: X:" + screenX + ", Y:" + screenY + " pointer:" + pointer);
        return false;
    }
}

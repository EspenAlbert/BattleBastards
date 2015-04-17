package com.tdt4240.RawHeroes.independent;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CameraController;
import com.tdt4240.RawHeroes.gameLogic.models.ICamera;
import com.tdt4240.RawHeroes.independent.inputListeners.ITouchDragged;
import com.tdt4240.RawHeroes.independent.inputListeners.TouchDown;
import com.tdt4240.RawHeroes.topLayer.screens.ActiveGameScreen;

import java.util.ArrayList;


/**
 * Created by espen on 19.01.2015.
 */
public class MyInputProcessor implements InputProcessor, GestureDetector.GestureListener {
    private static MyInputProcessor instance;
    private CameraController camera;
    private ActiveGameScreen screen;

    private MyInputProcessor() {}

    public static MyInputProcessor getInstance() {
        if(instance == null) {
            instance = new MyInputProcessor();
        }
        return instance;
    }

    private ArrayList<ITouchDragged> touchDraggedListeners = new ArrayList<ITouchDragged>();
    private ArrayList<TouchDown> touchDownsListeners = new ArrayList<TouchDown>();

    public void AddTouchDownListener(TouchDown listener) {
        touchDownsListeners.add(listener);
    }
    public void AddTouchDraggedListener(ITouchDragged listener) {
        touchDraggedListeners.add(listener);
    }

    public void setCamera(CameraController camera) {
        this.camera = camera;
    }
    public void removeListeners() {
        touchDownsListeners.clear();
        touchDraggedListeners.clear();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }


    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //screenY = Math.abs((screenY - GameConstants.RESOLUTION_HEIGHT) % 800);
        System.out.println("touch @ " + screenX + "," + screenY);

        for(TouchDown listener: touchDownsListeners) {
            listener.touchDown(screenX, screenY, pointer, button);
        }
        /*
        Vector2 cellCoordinate = camera.convertPixelCoordinateToCell(new Vector2(screenX, screenY));
        System.out.println("converted to cell: " + cellCoordinate.x + "," + cellCoordinate.y);

        if(cellCoordinate.x > 3) {
            camera.translate(0, 1);
        }
        else {
            camera.translate(0, -1);
        }
        screen.cellClicked(cellCoordinate);
        */
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        for(ITouchDragged listener : touchDraggedListeners) {
            listener.touchDragged(screenX, screenY, pointer);
        }
            return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        /*
        System.out.println("Scroll amount:" + amount);
        camera.zoomTest(amount);
        */
        return false;
    }

    public void setScreen(ActiveGameScreen screen) {
        this.screen = screen;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {

        System.out.println("Tapped" + " X:" + x + " , Y:" + y + " Count" + count);
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        System.out.println("Long pressX" + x + ",Y:" + y);
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        System.out.println("fling....");
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        System.out.println("pan");
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}

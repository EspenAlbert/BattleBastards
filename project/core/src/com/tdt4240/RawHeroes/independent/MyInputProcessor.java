package com.tdt4240.RawHeroes.independent;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CameraController;
import com.tdt4240.RawHeroes.gameLogic.models.ICamera;
import com.tdt4240.RawHeroes.independent.inputListeners.IFlingListener;
import com.tdt4240.RawHeroes.independent.inputListeners.ILongPress;
import com.tdt4240.RawHeroes.independent.inputListeners.ITouchDragged;
import com.tdt4240.RawHeroes.independent.inputListeners.TouchDown;
import com.tdt4240.RawHeroes.topLayer.screens.ActiveGameScreen;

import java.util.ArrayList;


/**
 * Created by espen on 19.01.2015.
 */
public class MyInputProcessor implements GestureDetector.GestureListener, InputProcessor {
    private static MyInputProcessor instance;

    private MyInputProcessor() {}

    public static MyInputProcessor getInstance() {
        if(instance == null) {
            instance = new MyInputProcessor();
        }
        return instance;
    }

    private ArrayList<ITouchDragged> touchDraggedListeners = new ArrayList<ITouchDragged>();
    private ArrayList<TouchDown> touchDownsListeners = new ArrayList<TouchDown>();
    private ArrayList<IFlingListener> flingListeners = new ArrayList<IFlingListener>();
    private ArrayList<ILongPress> longListeners = new ArrayList<ILongPress>();

    public void AddTouchDownListener(TouchDown listener) {
        touchDownsListeners.add(listener);
    }
    public void AddFlingListener(IFlingListener listener) {
        flingListeners.add(listener);
    }
    public void AddLongListener(ILongPress listener) {
        longListeners.add(listener);
    }
    public void AddTouchDraggedListener(ITouchDragged listener) {
        touchDraggedListeners.add(listener);
    }


    public void removeListeners() {
        touchDownsListeners.clear();
        touchDraggedListeners.clear();
    }


    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {

        for(TouchDown listener: touchDownsListeners) {
            listener.touchDown(x, y, count, button);
        }
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        for(ILongPress listener: longListeners) {
            listener.longPress(x, y);
        }
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        for(IFlingListener listener: flingListeners) {
            listener.fling(velocityX, velocityY, button);
        }
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

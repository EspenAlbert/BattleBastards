package com.tdt4240.RawHeroes.independent;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.models.ICamera;
import com.tdt4240.RawHeroes.independent.inputListeners.TouchDown;

import java.util.ArrayList;


/**
 * Created by espen on 19.01.2015.
 */
public class MyInputProcessor implements InputProcessor {
    private static MyInputProcessor instance;
    private ICamera camera;

    private MyInputProcessor() {}

    public static MyInputProcessor getInstance() {
        if(instance == null) {
            instance = new MyInputProcessor();
        }
        return instance;
    }

    private ArrayList<TouchDown> touchDownsListeners = new ArrayList<TouchDown>();

    public void AddTouchDownListener(TouchDown listener) {
        touchDownsListeners.add(listener);
    }

    public void setCamera(ICamera camera) {
        this.camera = camera;
    }
    public void removeListeners() {
        touchDownsListeners.clear();
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
        screenY = Math.abs((screenY - GameConstants.RESOLUTION_HEIGHT) % 800);

        for(TouchDown listener: touchDownsListeners) {
            listener.touchDown(screenX, screenY, pointer, button);
        }
        System.out.println("touch @ " + screenX + "," + screenY);
        Vector2 cellCoordinate = camera.convertPixelCoordinateToCell(new Vector2(screenX, screenY));
        System.out.println("converted to cell: " + cellCoordinate.x + "," + cellCoordinate.y);
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

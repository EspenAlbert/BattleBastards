package com.tdt4240.RawHeroes.independent;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CameraController;
import com.tdt4240.RawHeroes.gameLogic.models.ICamera;
import com.tdt4240.RawHeroes.independent.inputListeners.TouchDown;
import com.tdt4240.RawHeroes.topLayer.screens.ActiveGameScreen;

import java.util.ArrayList;


/**
 * Created by espen on 19.01.2015.
 */
public class MyInputProcessor implements InputProcessor {
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

    private ArrayList<TouchDown> touchDownsListeners = new ArrayList<TouchDown>();

    public void AddTouchDownListener(TouchDown listener) {
        touchDownsListeners.add(listener);
    }

    public void setCamera(CameraController camera) {
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
        //screenY = Math.abs((screenY - GameConstants.RESOLUTION_HEIGHT) % 800);

        for(TouchDown listener: touchDownsListeners) {
            listener.touchDown(screenX, screenY, pointer, button);
        }
        /*
        System.out.println("touch @ " + screenX + "," + screenY);
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
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        System.out.println("Scroll amount:" + amount);
        camera.zoomTest(amount);
        return false;
    }

    public void setScreen(ActiveGameScreen screen) {
        this.screen = screen;
    }
}

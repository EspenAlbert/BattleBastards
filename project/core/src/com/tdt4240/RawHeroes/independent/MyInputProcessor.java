package com.tdt4240.RawHeroes.independent;

import com.badlogic.gdx.InputProcessor;
import com.tdt4240.RawHeroes.independent.inputListeners.TouchDown;

import java.util.ArrayList;


/**
 * Created by espen on 19.01.2015.
 */
public class MyInputProcessor implements InputProcessor {
    private static MyInputProcessor instance;

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
        for(TouchDown listener: touchDownsListeners) {
            listener.touchDown(screenX, screenY, pointer, button);
        }
        System.out.println("touch..");
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

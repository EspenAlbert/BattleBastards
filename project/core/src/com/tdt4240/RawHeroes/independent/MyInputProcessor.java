package com.tdt4240.RawHeroes.independent;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.inputListeners.QuitGameTouchDown;
import com.tdt4240.RawHeroes.independent.inputListeners.IFlingListener;
import com.tdt4240.RawHeroes.independent.inputListeners.ILongPress;
import com.tdt4240.RawHeroes.independent.inputListeners.IPanListener;
import com.tdt4240.RawHeroes.independent.inputListeners.IPanStopListener;
import com.tdt4240.RawHeroes.independent.inputListeners.ITouchDragged;
import com.tdt4240.RawHeroes.independent.inputListeners.TouchDown;

import java.util.ArrayList;


/**
 * Created by espen on 19.01.2015.
 */
public class MyInputProcessor implements GestureDetector.GestureListener, InputProcessor {
    private static MyInputProcessor instance;
    private boolean active;
    private ArrayList<TouchDown> exceptionList = new ArrayList<TouchDown>();
    private TouchDown toBeRemoved;
    private boolean removeListener;


    private MyInputProcessor() {
        active = true;
    }

    public static MyInputProcessor getInstance() {
        if(instance == null) {
            instance = new MyInputProcessor();
        }
        return instance;
    }

    private ArrayList<IPanStopListener> panStopListeners = new ArrayList<IPanStopListener>();
    private ArrayList<IPanListener> panListeners = new ArrayList<IPanListener>();
    private ArrayList<ITouchDragged> touchDraggedListeners = new ArrayList<ITouchDragged>();
    private ArrayList<TouchDown> touchDownsListeners = new ArrayList<TouchDown>();
    private ArrayList<IFlingListener> flingListeners = new ArrayList<IFlingListener>();
    private ArrayList<ILongPress> longListeners = new ArrayList<ILongPress>();

    public void AddTouchDownListener(TouchDown listener) {
        touchDownsListeners.add(listener);
    }
    public void AddLongListener(ILongPress listener) {
        longListeners.add(listener);
    }
    public void AddPanListener(IPanListener listener) {
        panListeners.add(listener);
    }
    public void AddPanStopListener(IPanStopListener listener) {
        panStopListeners.add(listener);
    }


    public void removeListeners() {
        touchDownsListeners.clear();
        touchDraggedListeners.clear();
    }


    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        //if(!active) //active = true;
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if(!active) {
            for(TouchDown exceptionListener: exceptionList) {
                exceptionListener.touchDown(x, y, count, button);
            }
            return false;
        }
        if(removeListener) {
            touchDownsListeners.remove(toBeRemoved);
            toBeRemoved = null;
            removeListener = false;
        }
        for(TouchDown listener: touchDownsListeners) {
            listener.touchDown(x, y, count, button);
        }
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        if(!active) return false;
        for(ILongPress listener: longListeners) {
            listener.longPress(x, y);
        }
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if(!active) return false;
        for(IPanListener listener: panListeners) {
            listener.pan(x, y, deltaX, deltaY);
        }

        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        for(IPanStopListener listener: panStopListeners) {
            listener.panStop(x, y, pointer, button);
        }

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

    public void deactivateListeners() {
        active = false;
    }
    public void activateListeners() {
        active = true;
    }

    public void deactivateListenersExcept(TouchDown onlyActiveListener) {
        deactivateListeners();
        exceptionList.add(onlyActiveListener);
    }

    public void removeListener(TouchDown listener) {
        toBeRemoved = listener;
        removeListener = true;
    }
}

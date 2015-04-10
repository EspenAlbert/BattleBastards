package com.tdt4240.RawHeroes.gameLogic.controllers.cameraController;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.event.listener.ICameraListener;
import com.tdt4240.RawHeroes.gameLogic.models.ICamera;
import com.tdt4240.RawHeroes.independent.Directions;
import com.tdt4240.RawHeroes.independent.GameConstants;

import java.util.ArrayList;

/**
 * Created by espen1 on 15.03.2015.
 */
public class Player1Camera implements ICamera {

    private int x, y;
    private ArrayList<ICameraListener> listeners;

    public Player1Camera() {
        x = 0;
        y = 0;
        listeners = new ArrayList<ICameraListener>();
    }

    @Override
    public Vector2 convertPixelCoordinateToCell(Vector2 pixelCoordinate) {
        int xCell = Math.round(pixelCoordinate.x / (GameConstants.BUTTON_WIDTH + GameConstants.SPACE_BETWEEN)) + x;
        int yCell = Math.round(pixelCoordinate.y / (GameConstants.BUTTON_HEIGHT + GameConstants.SPACE_BETWEEN)) + y;
        return new Vector2(xCell, yCell);
    }

    @Override
    public void move(Directions direction) {
        switch (direction) {
            case NORTH:
                y += 1;
                cameraShifted(0, 1);
                break;
            case SOUTH:
                y -= 1;
                cameraShifted(0, -1);
                break;
            case EAST:
                x += 1;
                cameraShifted(1, 0);
                break;
            case WEST:
                x -= 1;
                cameraShifted(-1, 0);
                break;
        }
    }

    @Override
    public void addCameraListener(ICameraListener listener) {
        listeners.add(listener);
    }

    public void cameraShifted(int dx, int dy) {
        for(ICameraListener listener: listeners) {
            listener.cameraShifted(dx, dy);
        }
    }
}

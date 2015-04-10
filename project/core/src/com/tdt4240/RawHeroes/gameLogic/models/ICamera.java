package com.tdt4240.RawHeroes.gameLogic.models;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.event.listener.ICameraListener;
import com.tdt4240.RawHeroes.independent.Directions;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface ICamera {
    Vector2 convertPixelCoordinateToCell(Vector2 pixelCoordinate);
    void move(Directions direction);
    void addCameraListener(ICameraListener listener);
}

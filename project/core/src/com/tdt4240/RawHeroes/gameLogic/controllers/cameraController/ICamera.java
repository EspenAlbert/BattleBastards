package com.tdt4240.RawHeroes.gameLogic.controllers.cameraController;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface ICamera {
    Vector2 convertPixelCoordinateToCell(Vector2 pixelCoordinate);
}

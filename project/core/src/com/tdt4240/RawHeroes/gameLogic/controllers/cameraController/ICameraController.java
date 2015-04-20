package com.tdt4240.RawHeroes.gameLogic.controllers.cameraController;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.event.listener.ICameraListener;
import com.tdt4240.RawHeroes.independent.Directions;
import com.tdt4240.RawHeroes.independent.Position;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface ICameraController {
    Position convertPixelCoordinateToCell(Vector2 pixelCoordinate);
    Vector2 getScreenPixelCoordinate(float x, float y);
    void makeSureVisible(Position startPos, Position endPos);
}

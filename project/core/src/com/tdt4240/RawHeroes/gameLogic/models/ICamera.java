package com.tdt4240.RawHeroes.gameLogic.models;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;

/**
 * Created by espen1 on 20.04.2015.
 */
public interface ICamera {
    Matrix4 getProjectionMatrix();
    void update();
    OrthographicCamera getCamera();
    void translate(float dy);

}

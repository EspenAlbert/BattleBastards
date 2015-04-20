package com.tdt4240.RawHeroes.gameLogic.models;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.tdt4240.RawHeroes.independent.GameConstants;

/**
 * Created by espen1 on 20.04.2015.
 */
public class StandardCamera implements ICamera{

    private OrthographicCamera camera;

    public StandardCamera() {
        camera = new OrthographicCamera();
        camera.position.set(GameConstants.GAME_VISIBLE_WIDTH / 2, GameConstants.GAME_VISIBLE_HEIGHT / 2, 0);

    }

    public Matrix4 getProjectionMatrix() {
        return camera.combined;
    }

    public void update() {
        camera.update();
    }

    public void translate(float dy) {
        camera.translate(0, dy);
    }
    public OrthographicCamera getCamera() {
        return camera;
    }
}

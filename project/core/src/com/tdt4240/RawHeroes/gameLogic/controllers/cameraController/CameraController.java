package com.tdt4240.RawHeroes.gameLogic.controllers.cameraController;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tdt4240.RawHeroes.event.listener.ICameraListener;
import com.tdt4240.RawHeroes.gameLogic.models.ICamera;
import com.tdt4240.RawHeroes.independent.Directions;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.independent.Position;

import java.util.ArrayList;

/**
 * Created by espen1 on 15.03.2015.
 */
public class CameraController implements ICamera {

    private FitViewport viewport;
    private int x, y;
    private ArrayList<ICameraListener> listeners;
    private OrthographicCamera camera;


    public CameraController() {

        float aspectRatio = (float) GameConstants.RESOLUTION_HEIGHT / GameConstants.RESOLUTION_WIDTH;

        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConstants.GAME_WIDTH + GameConstants.EXTRA_SPACE_BUTTONS, GameConstants.GAME_HEIGHT, camera);

        viewport.apply();
        camera.position.set(GameConstants.GAME_WIDTH / 2, GameConstants.GAME_HEIGHT / 2, 0);

        listeners = new ArrayList<ICameraListener>();
    }

    @Override
    public Position convertPixelCoordinateToCell(Vector2 pixelCoordinate) {
        Vector3 realCoordinate = viewport.unproject(new Vector3(pixelCoordinate.x, pixelCoordinate.y, 0));
        System.out.println("The real coordinate: " + realCoordinate.x + "," + realCoordinate.y);

        int xCell = (int) realCoordinate.x;
        int yCell = (int) realCoordinate.y;
        return new Position(xCell, yCell);
    }

    public void zoomTest(int amount) {

        camera.zoom += amount * 0.02;

        camera.zoom = MathUtils.clamp(camera.zoom, 0.1f, 100 / camera.viewportWidth);

        float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
        float effectiveViewportHeight = camera.viewportHeight * camera.zoom;

        camera.position.x = MathUtils.clamp(camera.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        camera.position.y = MathUtils.clamp(camera.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
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

    public Matrix4 getProjectionMatrix() {
        return camera.combined;
    }

    public void update() {
        camera.update();
    }

    public void translate(int x, int y) {
        camera.translate(x, y);
    }

    @Override
    public void makeSureVisible(Position startPos, Position endPos) {
        //TODO: Implement logic to make sure positions is visible...
    }

    public void resize(int width, int height) {
        viewport.update(width,height);
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
    }

    public Vector2 getScreenPixelCoordinate(float x, float y) {
        return viewport.project(new Vector2(x, y));
    }
}

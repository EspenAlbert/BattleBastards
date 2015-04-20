package com.tdt4240.RawHeroes.gameLogic.controllers.cameraController;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tdt4240.RawHeroes.gameLogic.models.ICamera;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.independent.Position;

/**
 * Created by espen1 on 15.03.2015.
 */
public class CameraController implements ICameraController {

    private CameraTranslator ct;
    private FitViewport viewport;
    private ICamera camera;


    public CameraController(ICamera camera) {
        this.camera = camera;
        viewport = new FitViewport(GameConstants.GAME_VISIBLE_WIDTH + GameConstants.EXTRA_SPACE_BUTTONS, GameConstants.GAME_VISIBLE_HEIGHT, camera.getCamera());
        viewport.apply();
    }

    @Override
    public Position convertPixelCoordinateToCell(Vector2 pixelCoordinate) {
        Vector3 realCoordinate = viewport.unproject(new Vector3(pixelCoordinate.x, pixelCoordinate.y, 0));
        int xCell = (int) realCoordinate.x;
        int yCell = (int) realCoordinate.y;
        return new Position(xCell, yCell);
    }

    public void translate(int x, int y) {
        Position p = convertPixelCoordinateToCell(new Vector2(0,GameConstants.RESOLUTION_HEIGHT-10));
        if(ct == null) {
            ct = new CameraTranslator(camera, this, y);
            ct.start();
        } else {
            ct.newFinishPos(y);
        }
    }

    @Override
    public void makeSureVisible(Position startPos, Position endPos) {
        Position p = convertPixelCoordinateToCell(new Vector2(0,GameConstants.RESOLUTION_HEIGHT-10));
        int difference = endPos.getY() - p.getY();
        if(Math.abs(difference) != 2) {
            int transfer = difference -2;
            translate(0, transfer);
        }
    }

    public void resize(int width, int height) {
        viewport.update(width,height);
        camera.getCamera().position.set(camera.getCamera().viewportWidth/2,camera.getCamera().viewportHeight/2,0);
    }

    public Vector2 getScreenPixelCoordinate(float x, float y) {
        return viewport.project(new Vector2(x, y));
    }
    public void finishedMovingCamera() {
        ct = null;
    }

    public void dispose() {
        while(ct != null)
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}

package com.tdt4240.RawHeroes.gameLogic.controllers.cameraController;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.models.ICamera;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.independent.Position;

/**
 * Created by espen1 on 18.04.2015.
 */
public class CameraTranslator extends Thread {

    private boolean up;
    private float f;
    private ICamera camera;
    private CameraController cameraController;
    private int distance;
    private Position finishPos;
    private final float cameraSpeed = GameConstants.CAMERA_SPEED;
    private float middleDistance;

    public CameraTranslator(ICamera camera, CameraController cameraController, int distance) {
        this.camera = camera;
        this.cameraController = cameraController;
        newFinishPos(distance);
        setup(distance);
    }

    private void setup(int distance) {
        this.distance = Math.abs(distance);
        up = distance > 0;
        middleDistance = 0.5f;
        f = 0.01f;
    }

    @Override
    public void run() {
        while(f< middleDistance) {
            f += cameraSpeed;
            camera.translate(up ? cameraSpeed : -cameraSpeed);
            try {
                this.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(finishPosIsCorrect()) {
            cameraController.finishedMovingCamera();
        } else {
            run();
        }
    }

    private boolean finishPosIsCorrect() {
        Position currentPos = cameraController.convertPixelCoordinateToCell(new Vector2(0, GameConstants.RESOLUTION_HEIGHT-10));
        if(currentPos.equals(finishPos)) {
            return true;
        } else {
            int difference = finishPos.getY() - currentPos.getY();
            setup(difference);
            return false;
        }

    }
    private final int maxHeight = (int) GameConstants.GAME_VISIBLE_HEIGHT;
    public void newFinishPos(int movement) {
        finishPos = cameraController.convertPixelCoordinateToCell(new Vector2(0, GameConstants.RESOLUTION_HEIGHT-10)).add(0, movement);
        if(finishPos.getY() < 0) finishPos = new Position(0, -1);
        else if(finishPos.getY() > maxHeight) finishPos = new Position(0,maxHeight);

    }
}

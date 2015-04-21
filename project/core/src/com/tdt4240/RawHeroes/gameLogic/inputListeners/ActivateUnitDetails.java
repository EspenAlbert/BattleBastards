package com.tdt4240.RawHeroes.gameLogic.inputListeners;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CameraController;
import com.tdt4240.RawHeroes.independent.MyInputProcessor;
import com.tdt4240.RawHeroes.independent.Position;
import com.tdt4240.RawHeroes.independent.inputListeners.ILongPress;
import com.tdt4240.RawHeroes.view.customUIElements.unitDetailRenderer.UnitDetailRenderer;

/**
 * Created by William on 18.04.2015.
 */
public class ActivateUnitDetails implements ILongPress {

    private CameraController camera;
    private TouchDownRemoveUnitDetails removeUnitDetails;
    private UnitDetailRenderer unitDetailRenderer;

    public ActivateUnitDetails(UnitDetailRenderer unitDetailRenderer, CameraController camera) {
        this.unitDetailRenderer = unitDetailRenderer;
        this.camera = camera;
        this.removeUnitDetails = removeUnitDetails;
    }

    @Override
    public void longPress(float x, float y) {
        Position pos = camera.convertPixelCoordinateToCell(new Vector2(x, y));
        unitDetailRenderer.showUnitDetails(pos);
        MyInputProcessor.getInstance().deactivateListenersExcept(new TouchDownRemoveUnitDetails(unitDetailRenderer));

    }
}

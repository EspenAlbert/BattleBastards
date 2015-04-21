package com.tdt4240.RawHeroes.gameLogic.inputListeners;

import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CameraController;
import com.tdt4240.RawHeroes.independent.MyInputProcessor;
import com.tdt4240.RawHeroes.independent.inputListeners.TouchDown;
import com.tdt4240.RawHeroes.view.customUIElements.unitDetailRenderer.UnitDetailRenderer;
import com.tdt4240.RawHeroes.view.topLayer.GameView;

/**
 * Created by William on 18.04.2015.
 */
public class TouchDownRemoveUnitDetails implements TouchDown {

    private boolean firstTouch;
    private UnitDetailRenderer unitDetailRenderer;

    public TouchDownRemoveUnitDetails(UnitDetailRenderer unitDetailRenderer){
        this.unitDetailRenderer = unitDetailRenderer;
        firstTouch = true;
    }

    @Override
    public boolean touchDown(float screenX, float screenY, int pointer, int button) {
        if(firstTouch) {
            firstTouch = false;
            return false;
        }
        unitDetailRenderer.hideUnitDetails();
        System.out.println("should have hidden unitDetails...");
        return false;
    }
}

package com.tdt4240.RawHeroes.gameLogic.inputListeners;

import com.tdt4240.RawHeroes.independent.inputListeners.TouchDown;
import com.tdt4240.RawHeroes.topLayer.screens.ScreenStateManager;

/**
 * Created by espen1 on 20.04.2015.
 */
public class QuitGameTouchDown implements TouchDown {
    private ScreenStateManager gsm;

    public QuitGameTouchDown(ScreenStateManager gsm) {
        this.gsm = gsm;
    }

    @Override
    public boolean touchDown(float screenX, float screenY, int pointer, int button) {
        gsm.popOnly();
        return false;
    }
}

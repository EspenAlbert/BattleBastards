package com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse;

import com.tdt4240.RawHeroes.event.listener.IAnimationListener;
import com.tdt4240.RawHeroes.gameLogic.models.UnitRenderModel;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface IRenderObject extends IRender, IAnimationListener {
    void changeRenderMode(RenderMode renderMode);
    RenderMode getRenderMode();
    UnitRenderModel getRenderModel();
}

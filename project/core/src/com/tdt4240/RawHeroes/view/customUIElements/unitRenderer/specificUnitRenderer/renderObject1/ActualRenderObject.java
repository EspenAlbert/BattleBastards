package com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.renderObject1;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.IRenderObject;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.RenderMode;

/**
 * Created by espen1 on 27.02.2015.
 */
public class ActualRenderObject implements IRenderObject{
    public ActualRenderObject() {
        System.out.println("A render object has been created");
    }

    @Override
    public void changeRenderMode(RenderMode renderMode) {

    }

    @Override
    public RenderMode getRenderMode() {
        return null;
    }

    @Override
    public void render(SpriteBatch batch, Vector2 pos) {
        System.out.println("about to render @ pos:" + pos.x + "," + pos.y);
    }
}

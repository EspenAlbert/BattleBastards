package com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.renderObject1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitAnimationController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.SimpleUnitAnimationController;
import com.tdt4240.RawHeroes.independent.TextureChanger;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.IRenderObject;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.RenderMode;

/**
 * Created by espen1 on 27.02.2015.
 */
public class ActualRenderObject implements IRenderObject{

    public static Texture texture;
    private Sprite sprite;
    private IUnitAnimationController anicont;

    public ActualRenderObject(boolean isPlayer1) {
        anicont = new SimpleUnitAnimationController();
        texture = new Texture(Gdx.files.internal("units/soldierSheet.png"));
        if (isPlayer1)texture = TextureChanger.changeColor(texture, Color.RED);
        else texture = TextureChanger.changeColor(texture, Color.BLUE);
        TextureRegion region = anicont.getActiveFrame(texture);
        sprite = new Sprite(region);
        sprite.setSize(1,2);
        System.out.println("A render object has been created");
    }

    @Override
    public void changeRenderMode(RenderMode renderMode) {
        System.out.println("Switched render mode to:"  + renderMode);
    }

    @Override
    public RenderMode getRenderMode() {
        return null;
    }

    @Override
    public void render(SpriteBatch batch, Vector2 pos) {
        sprite.setPosition(pos.x, pos.y);
        sprite.draw(batch);
    }

    @Override
    public void animationChanged() {

    }

    @Override
    public void nextFrame() {

    }

    @Override
    public void frameChanged() {

    }
}

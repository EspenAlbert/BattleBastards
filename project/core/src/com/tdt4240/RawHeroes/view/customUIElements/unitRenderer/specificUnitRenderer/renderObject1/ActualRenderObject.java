package com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.renderObject1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.independent.TextureChanger;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.IRenderObject;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.RenderMode;

/**
 * Created by espen1 on 27.02.2015.
 */
public class ActualRenderObject implements IRenderObject{

    public static Texture texture;
    private Sprite sprite;

    public ActualRenderObject(boolean isPlayer1) {
        if (isPlayer1)texture = TextureChanger.changeColor(new Texture(Gdx.files.internal("units/soldiercolor.png")), Color.RED);
        else texture = TextureChanger.changeColor(new Texture(Gdx.files.internal("units/soldiercolor.png")), Color.BLUE);
        sprite = new Sprite(texture);
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
}

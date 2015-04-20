package com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.renderObject1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.createUnits.units.standardUnit.StandardUnitSheet;
import com.tdt4240.RawHeroes.event.events.AnimationEvent;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.IUnitAnimationController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.SimpleUnitAnimationController;
import com.tdt4240.RawHeroes.gameLogic.models.ISpritesheet;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.independent.AnimationConstants;
import com.tdt4240.RawHeroes.independent.TextureChanger;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.IRenderObject;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.RenderMode;

/**
 * Created by espen1 on 27.02.2015.
 */
public class ActualRenderObject implements IRenderObject{

    public ISpritesheet sheet;
    private IUnit unit;
    private Sprite sprite;
    private RenderMode renderMode;

    public ActualRenderObject(IUnit unit) {
        this.unit = unit;
        sheet = new StandardUnitSheet("units/soldierSheet.png");
        if (unit.isPlayer1Unit())sheet.setTexture (TextureChanger.changeColor(sheet.getTexture(), Color.RED));
        else sheet.setTexture (TextureChanger.changeColor(sheet.getTexture(), Color.BLUE));
        TextureRegion region = sheet.getActiveFrame(0, 0);
        sprite = new Sprite(region);
        sprite.setSize(1.5f,2);
        System.out.println("A render object has been created");
    }

    @Override
    public void changeRenderMode(RenderMode renderMode) {
        this.renderMode = renderMode;
        unit.setActiveAnimation(renderMode);
    }

    @Override
    public RenderMode getRenderMode() {
        return this.renderMode;
    }

    @Override
    public void render(SpriteBatch batch, Vector2 pos) {
        sprite.setPosition(pos.x, pos.y);
        sprite.draw(batch);
    }

    @Override
    public void animationChanged(AnimationEvent event) {
        this.sprite = new Sprite(sheet.getActiveFrame(event.getActiveFrame(), event.getActiveAnimation()));
        sprite.setSize(1.5f,2);
    }

}

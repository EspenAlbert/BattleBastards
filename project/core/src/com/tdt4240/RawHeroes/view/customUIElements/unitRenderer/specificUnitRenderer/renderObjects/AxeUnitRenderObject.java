package com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.renderObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.createUnits.units.axeUnit.AxeUnitSheet;
import com.tdt4240.RawHeroes.createUnits.units.standardUnit.StandardUnitSheet;
import com.tdt4240.RawHeroes.event.events.AnimationEvent;
import com.tdt4240.RawHeroes.gameLogic.models.ISpritesheet;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.gameLogic.models.UnitRenderModel;
import com.tdt4240.RawHeroes.independent.AnimationConstants;
import com.tdt4240.RawHeroes.independent.TextureChanger;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.IRenderObject;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.RenderMode;

import java.io.Serializable;

/**
 * Created by espen1 on 27.02.2015.
 */
public class AxeUnitRenderObject implements IRenderObject, Serializable{

    private UnitRenderModel renderModel;
    private Sprite sprite;
    private RenderMode renderMode;
    private float leftShift;

    public AxeUnitRenderObject(UnitRenderModel renderModel) {
        this.renderModel = renderModel;
        leftShift = this.renderModel.isTurnedRight() ? 0 : 0.5f;
        if (renderModel.isRed())renderModel.getSheet().setTexture(TextureChanger.changeColor(renderModel.getSheet().getTexture(), Color.RED));
        else renderModel.getSheet().setTexture(TextureChanger.changeColor(renderModel.getSheet().getTexture(), Color.BLUE));
        TextureRegion region = renderModel.getSheet().getActiveFrame(0, 0);
        sprite = new Sprite(region);
        sprite.setSize(1.5f,2);
        this.changeRenderMode(RenderMode.STATIC);
        System.out.println("A render object has been created");
    }

    @Override
    public void changeRenderMode(RenderMode renderMode) {
        this.renderMode = renderMode;
        this.renderModel.setActiveAnimation(renderMode);
    }
    @Override
    public UnitRenderModel getRenderModel(){
        return this.renderModel;
    }

    @Override
    public RenderMode getRenderMode() {
        return this.renderMode;
    }

    @Override
    public void render(SpriteBatch batch, Vector2 pos) {
        sprite.setPosition(pos.x - leftShift, pos.y);
        sprite.draw(batch);
    }

    @Override
    public void animationChanged(AnimationEvent event) {
        this.sprite = new Sprite(renderModel.getSheet().getActiveFrame(event.getActiveFrame(), event.getActiveAnimation()));
        sprite.setSize(1.5f,2);
        leftShift = this.renderModel.isTurnedRight() ? 0 : 0.5f;
        switch (event.getActiveAnimation()){
            case AnimationConstants.IDLE_RIGHT:
                this.renderMode = RenderMode.STATIC;
                break;
            case AnimationConstants.IDLE_LEFT:
                this.renderMode = RenderMode.STATIC;
                break;
            case AnimationConstants.MOVE_LEFT:
                this.renderMode = RenderMode.MOVING;
                break;
            case AnimationConstants.MOVE_RIGHT:
                this.renderMode = RenderMode.MOVING;
                break;
            case AnimationConstants.ATK_LEFT:
                this.renderMode = RenderMode.ATTACKING;
                break;
            case AnimationConstants.ATK_RIGHT:
                this.renderMode = RenderMode.ATTACKING;
                break;
            case AnimationConstants.HURT_LEFT:
                this.renderMode = RenderMode.HURT;
                break;
            case AnimationConstants.HURT_RIGHT:
                this.renderMode = RenderMode.HURT;
                break;
            case AnimationConstants.DEAD:
                this.renderMode = RenderMode.KILLED;
                break;
        }
    }

}

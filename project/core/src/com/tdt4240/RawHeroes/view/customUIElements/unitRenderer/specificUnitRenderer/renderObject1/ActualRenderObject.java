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


    public static Texture texture = new Texture(Gdx.files.internal("units/soldierpluss.png")),
    attack=new Texture(Gdx.files.internal("units/soldierplussAttack.png")),
    hurt=new Texture(Gdx.files.internal("units/soldierplussHurt2.png")),
    walk=new Texture(Gdx.files.internal("units/soldierplussWalk.png")),
    dead=new Texture(Gdx.files.internal("units/Dead.png"));
    private Sprite sprite;
    private RenderMode renderMode;
    private boolean walking=false;
    private int timer;

    public ActualRenderObject(boolean isPlayer1) {
        sprite = new Sprite(texture);
        sprite.setSize(1,2);
        System.out.println("A render object has been created");
    }

    @Override
    public void changeRenderMode(RenderMode renderMode) {
        this.renderMode = renderMode;
        if (renderMode == RenderMode.ATTACKING) {
            sprite = new Sprite(attack);
            sprite.setSize(2,2);
            timer=13;
        } else if (renderMode == RenderMode.KILLED) {
            sprite = new Sprite(dead);
            sprite.setSize(1,1);
            timer=1;
        } else if (renderMode == RenderMode.HURT) {
            sprite = new Sprite(hurt);
            sprite.setSize(1, 2);
            timer=20;
        } else if (renderMode == RenderMode.STATIC && walking) {
            sprite = new Sprite(texture);
            sprite.setSize(1, 2);
            timer = 10;
        } else if (renderMode == RenderMode.STATIC) {
            sprite = new Sprite(texture);
            sprite.setSize(1, 2);
            timer=1;
        } else if (renderMode == RenderMode.MOVING) {
            sprite = new Sprite(walk);
            sprite.setSize(1, 2);
            timer = 10;
            walking = true;
        }
        //System.out.println("Switched render mode to:"  + renderMode);
    }

    public void setWalkingFalse(){
        walking=false;
    }

    @Override
    public RenderMode getRenderMode() {
        return renderMode;
    }

    @Override
    public void render(SpriteBatch batch, Vector2 pos) {
        sprite.setPosition(pos.x, pos.y);
        sprite.draw(batch);
        if(!(renderMode==RenderMode.STATIC||renderMode==RenderMode.KILLED)||walking){
            timer--;
        }
        if(timer==0){
            if(walking&&renderMode==RenderMode.STATIC){
                changeRenderMode(RenderMode.MOVING);
            }else{
                changeRenderMode(RenderMode.STATIC);
            }
        }
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

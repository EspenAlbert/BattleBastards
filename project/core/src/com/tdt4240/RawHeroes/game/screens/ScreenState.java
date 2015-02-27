package com.tdt4240.RawHeroes.game.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tdt4240.RawHeroes.game.launcher.RawHeroesOfBegredeligeStudenter;

/**
 * Created by espen1 on 27.02.2015.
 */
public abstract class ScreenState {
    protected ScreenStateManager gsm;
    protected RawHeroesOfBegredeligeStudenter game;

    protected SpriteBatch spriteBatch;
    protected OrthographicCamera cam;

    protected ScreenState(ScreenStateManager gsm) {
        this.gsm = gsm;
        game = gsm.getGame();
        spriteBatch = game.getSpriteBatch();
    }

    public abstract void update(float dt);

    public abstract void render();

    public abstract void dispose();
}

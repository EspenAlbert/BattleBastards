package com.tdt4240.RawHeroes.topLayer.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tdt4240.RawHeroes.network.client.ClientConnection;
import com.tdt4240.RawHeroes.network.client.IClientConnection;
import com.tdt4240.RawHeroes.topLayer.launcher.BattleBastards;

/**
 * Created by espen1 on 27.02.2015.
 */
public abstract class
        ScreenState {
    protected ScreenStateManager gsm;
    protected BattleBastards launcher;
    protected IClientConnection clientConnection = null;

    protected SpriteBatch spriteBatch;

    protected ScreenState(ScreenStateManager gsm) {
        this.gsm = gsm;
        launcher = gsm.getGame();
        spriteBatch = launcher.getSpriteBatch();
    }

    public abstract void update(float dt);

    public abstract void render();

    public abstract void dispose();

    public void resize(int width, int height) {

    }
}

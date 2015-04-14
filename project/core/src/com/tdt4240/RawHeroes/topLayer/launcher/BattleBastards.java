package com.tdt4240.RawHeroes.topLayer.launcher;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.topLayer.screens.ScreenStateManager;
import com.tdt4240.RawHeroes.independent.MyInputProcessor;

import java.util.ArrayList;

public class BattleBastards extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    private ScreenStateManager gameScreenManager;

    @Override
	public void create () {
		batch = new SpriteBatch();
        Gdx.graphics.setTitle("GAME NAME");
		img = new Texture("badlogic.jpg");
        Gdx.graphics.setDisplayMode(GameConstants.RESOLUTION_WIDTH, GameConstants.RESOLUTION_HEIGHT, false);
        Gdx.input.setInputProcessor(MyInputProcessor.getInstance());
        gameScreenManager = new ScreenStateManager(this);

	}


	@Override
	public void render () {
        gameScreenManager.update(Gdx.graphics.getDeltaTime());
        gameScreenManager.render();

/*
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
		batch.begin();
        for(Sprite sprite: sprites) {
            sprite.draw(batch);
        }
		batch.end();
*/
	}

    @Override
    public void resize(int width, int height){
        gameScreenManager.resize(width, height);
    }

    public SpriteBatch getSpriteBatch() {
        return batch;
    }
}

package com.tdt4240.RawHeroes.topLayer.launcher;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tdt4240.RawHeroes.topLayer.screens.ScreenStateManager;
import com.tdt4240.RawHeroes.independent.MyInputProcessor;

public class BattleBastards extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    private ScreenStateManager gameScreenManager;

    @Override
	public void create () {
		batch = new SpriteBatch();
        Gdx.graphics.setTitle("GAME NAME");
		img = new Texture("badlogic.jpg");
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
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
		*/
	}

    public SpriteBatch getSpriteBatch() {
        return batch;
    }
}

package com.tdt4240.RawHeroes.game.launcher;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tdt4240.RawHeroes.game.screens.ScreenStateManager;
import com.tdt4240.RawHeroes.input.MyInputProcessor;

public class RawHeroesOfBegredeligeStudenter extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    private ScreenStateManager gameScreenManager;

    @Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
        gameScreenManager = new ScreenStateManager(this);
        Gdx.input.setInputProcessor(MyInputProcessor.getInstance());

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

package com.tdt4240.RawHeroes.topLayer.launcher;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.createGame.factory.BoardFactory;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.topLayer.screens.ScreenStateManager;
import com.tdt4240.RawHeroes.independent.MyInputProcessor;
import com.tdt4240.RawHeroes.view.customUIElements.boardRenderer.BoardRenderer;

import java.util.ArrayList;

public class BattleBastards extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    private ScreenStateManager gameScreenManager;
    BoardRenderer board;

    @Override
	public void create () {
		batch = new SpriteBatch();
        //TODO: This depends on your computer... Therefore keeping both options available...
        //board = new BoardRenderer(BoardFactory.getInstance().getBoard("STANDARD-BOARD"), true);
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
        //board.render(batch, new Vector2(100, 100));

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

package com.tdt4240.RawHeroes.topLayer.launcher;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.createGame.factory.BoardFactory;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.topLayer.screens.ScreenStateManager;
import com.tdt4240.RawHeroes.independent.MyInputProcessor;
import com.tdt4240.RawHeroes.view.customUIElements.boardRenderer.BoardRenderer;

public class BattleBastards extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    private ScreenStateManager gameScreenManager;
    BoardRenderer board;

    @Override
	public void create () {
		batch = new SpriteBatch();
        board = new BoardRenderer(BoardFactory.getInstance().getBoard("STANDARD-BOARD"), true);
        Gdx.graphics.setTitle("GAME NAME");
		img = new Texture("badlogic.jpg");
        Gdx.graphics.setDisplayMode(GameConstants.RESOLUTION_WIDTH, GameConstants.RESOLUTION_HEIGHT, false);
//        Gdx.input.setInputProcessor(MyInputProcessor.getInstance());
//        gameScreenManager = new ScreenStateManager(this);

	}

	@Override
	public void render () {
//        gameScreenManager.update(Gdx.graphics.getDeltaTime());
//        gameScreenManager.render();
          board.render(batch, new Vector2(100, 100));

	}

    public SpriteBatch getSpriteBatch() {
        return batch;
    }
}

package com.tdt4240.RawHeroes.topLayer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.BoardController;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.BoardMover;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.IBoardController;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.IBoardMover;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.network.client.ClientConnection;
import com.tdt4240.RawHeroes.view.topLayer.GameView;

/**
 * Created by espen1 on 27.02.2015.
 */
public class ActiveGameScreen extends ScreenState {
    private final GameView gameView;
    private final IBoardMover boardMover;
    private final IBoardController boardController;
    private final IBoard board;
    private final boolean iAmPlayer1;

    public ActiveGameScreen(ScreenStateManager gsm, Game game) {
        super(gsm);
        board = game.getBoard();
        System.out.println("in active game screen!!!!!");
        iAmPlayer1 = ClientConnection.getInstance().getUsername().equals(game.getPlayer1Nickname());
        gameView = new GameView(board, iAmPlayer1);
        board.addBoardListener(gameView);

        boardMover = new BoardMover(board);
        boardMover.executeMoves(game.getLastMoves());
        boardController = new BoardController(board, boardMover, game.getMoveCount());
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.36f, 0.32f, 0.27f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}

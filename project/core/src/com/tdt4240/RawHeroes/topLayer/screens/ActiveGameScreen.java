package com.tdt4240.RawHeroes.topLayer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.tdt4240.RawHeroes.gameLogic.cell.CellStatus;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.BoardController;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.BoardControllerStateEvent;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.BoardControllerStateListener;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.BoardMover;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.IBoardController;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.IBoardMover;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CameraController;
import com.tdt4240.RawHeroes.gameLogic.inputListeners.MoveBoardTouchDraggedListener;
import com.tdt4240.RawHeroes.gameLogic.inputListeners.TouchListenerActiveGameScreen;
import com.tdt4240.RawHeroes.gameLogic.models.ICamera;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.independent.MyInputProcessor;
import com.tdt4240.RawHeroes.independent.Position;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.network.client.ClientConnection;
import com.tdt4240.RawHeroes.view.customUIElements.hudRenderer.HudRenderer;
import com.tdt4240.RawHeroes.view.topLayer.GameView;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public class ActiveGameScreen extends ScreenState{
    public static final float ButtonXPos =  ((float) 7 / 8) * GameConstants.RESOLUTION_WIDTH;


    private final GameView gameView;
    private final HudRenderer hud;
    private final IBoardMover boardMover;
    private final IBoardController boardController;
    private final IBoard board;
    private final boolean iAmPlayer1;
    private final CameraController cameraController;
    private SpriteBatch hudBatch;


    public ActiveGameScreen(ScreenStateManager gsm, Game game){
        super(gsm);
        board = game.getBoard();
        System.out.println("in active game screen!!!!!");
        //iAmPlayer1 = ClientConnection.getInstance().getUsername().equals(game.getPlayer1Nickname());
        iAmPlayer1 = false;
        if(!iAmPlayer1) {
            board.convertCellsToOtherPlayer();
        }
        cameraController = new CameraController();


        boardMover = new BoardMover(board);
        gameView = new GameView(board, iAmPlayer1, cameraController);
        boardController = new BoardController(board, boardMover, game.getMoveCount());
        hud = new HudRenderer(boardController);

        boardMover.addMoveListener(gameView);
        board.addBoardListener(gameView);

        boardMover.executeMoves(game.getLastMoves());
        hudBatch = new SpriteBatch(5);
        resize(GameConstants.RESOLUTION_WIDTH, GameConstants.RESOLUTION_HEIGHT);
    }
    private void initializeTouchListener() {
        GestureDetector gd = new GestureDetector(MyInputProcessor.getInstance());
        Gdx.input.setInputProcessor(gd);
        MyInputProcessor.getInstance().AddTouchDownListener(new TouchListenerActiveGameScreen(boardController, cameraController, this));
        MyInputProcessor.getInstance().AddTouchDraggedListener(new MoveBoardTouchDraggedListener(cameraController));
        initialized = true;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {
        initializeWhenViewReady();
        Gdx.gl.glClearColor(0.36f, 0.32f, 0.27f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        cameraController.update();
        spriteBatch.setProjectionMatrix(cameraController.getProjectionMatrix());
        gameView.render(spriteBatch);
        spriteBatch.end();
        hud.render(hudBatch);
    }

    private boolean initialized = false;
    private void initializeWhenViewReady() {
        if(!initialized && gameView.noAnimationWaiting()) initializeTouchListener();
    }

    @Override
    public void dispose() {
    }
    @Override
    public void resize(int width, int height) {
        cameraController.resize(width, height);
    }

    public void cellClicked(Position cellCoordinate) {
        board.switchModeOnCell(cellCoordinate, CellStatus.SELECTED);
    }

    public void backToMainMenu(){
        this.gsm.popState();
    }

}

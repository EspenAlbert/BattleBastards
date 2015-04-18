package com.tdt4240.RawHeroes.topLayer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.tdt4240.RawHeroes.gameLogic.cell.CellStatus;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.BoardController;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.BoardMover;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.IBoardController;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.IBoardMover;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CameraController;
import com.tdt4240.RawHeroes.gameLogic.inputListeners.TouchListenerActiveGameScreen;
import com.tdt4240.RawHeroes.gameLogic.models.ICamera;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.independent.MyInputProcessor;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.network.client.ClientConnection;
import com.tdt4240.RawHeroes.view.topLayer.GameView;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public class ActiveGameScreen extends ScreenState {
    public static final float ButtonXPos =  ((float) 7 / 8) * GameConstants.RESOLUTION_WIDTH;


    private final GameView gameView;
    private final IBoardMover boardMover;
    private final IBoardController boardController;
    private final IBoard board;
    private final boolean iAmPlayer1;
    private final CameraController cameraController;
    private final Sprite testSprite3;
    public static Texture ordinaryCell = new Texture(Gdx.files.internal("cell.png"));
    private SpriteBatch hudBatch;

    private Skin skin;

    private TextButton sendButton;
    private TextButton actionButton;
    private TextButton abortButton;

    private Label energyLabel;


    public ActiveGameScreen(ScreenStateManager gsm, Game game) {
        super(gsm);
        board = game.getBoard();
        System.out.println("in active game screen!!!!!");
        //iAmPlayer1 = ClientConnection.getInstance().getUsername().equals(game.getPlayer1Nickname());
        iAmPlayer1 = true;
        cameraController = new CameraController();

        skin = new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas(Gdx.files.internal("uiskin.atlas")));


        int buttonWidth = GameConstants.RESOLUTION_WIDTH - 7*GameConstants.CELL_WIDTH;
        int buttonHeight = GameConstants.RESOLUTION_HEIGHT /4;

        sendButton = new TextButton("Done", skin);
        sendButton.setSize(buttonWidth, buttonHeight);
        actionButton= new TextButton("Action", skin);
        actionButton.setSize(buttonWidth, buttonHeight);
        abortButton= new TextButton("Quit", skin);
        abortButton.setSize(buttonWidth, buttonHeight);
//TODO ikke bare statisk streng, men faktisk energi
        energyLabel = new Label("47/100",skin);
        energyLabel.setSize(buttonWidth, buttonHeight);
        energyLabel.setAlignment(0);
        energyLabel.setColor(1, 1, 1, 1);


        sendButton.setPosition(GameConstants.RESOLUTION_WIDTH-sendButton.getWidth(), abortButton.getHeight() + actionButton.getHeight());
        actionButton.setPosition(GameConstants.RESOLUTION_WIDTH - actionButton.getWidth(), abortButton.getHeight());
        abortButton.setPosition(GameConstants.RESOLUTION_WIDTH-abortButton.getWidth(), 0);
        energyLabel.setPosition(GameConstants.RESOLUTION_WIDTH-energyLabel.getWidth(), abortButton.getHeight() + actionButton.getHeight() + sendButton.getHeight());

        boardMover = new BoardMover(board);
        gameView = new GameView(board, iAmPlayer1,cameraController,boardMover);
        board.addBoardListener(gameView);
        boardMover.executeMoves(game.getLastMoves());
        boardController = new BoardController(board, boardMover, game.getMoveCount());
        Gdx.input.setInputProcessor(MyInputProcessor.getInstance());
        MyInputProcessor.getInstance().AddTouchDownListener(new TouchListenerActiveGameScreen(boardController, cameraController, this));
       // MyInputProcessor.getInstance().setCamera(cameraController);
       // MyInputProcessor.getInstance().setScreen(this);

        testSprite3 = new Sprite(ordinaryCell);
        testSprite3.setSize(100, 100);
        testSprite3.setPosition(ButtonXPos, 100);
        hudBatch = new SpriteBatch(5);
        resize(GameConstants.RESOLUTION_WIDTH, GameConstants.RESOLUTION_HEIGHT);


    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.36f, 0.32f, 0.27f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        cameraController.update();
        spriteBatch.setProjectionMatrix(cameraController.getProjectionMatrix());
        gameView.render(spriteBatch);
        spriteBatch.end();
        hudBatch.begin();

        sendButton.draw(hudBatch, 1);
        abortButton.draw(hudBatch, 1);
        actionButton.draw(hudBatch, 1);
        energyLabel.draw(hudBatch, 1);
        hudBatch.end();
    }

    @Override
    public void dispose() {

    }
    @Override
    public void resize(int width, int height) {
        cameraController.resize(width, height);
    }

    public void cellClicked(Vector2 cellCoordinate) {
        board.switchModeOnCell(cellCoordinate, CellStatus.SELECTED);
    }
}

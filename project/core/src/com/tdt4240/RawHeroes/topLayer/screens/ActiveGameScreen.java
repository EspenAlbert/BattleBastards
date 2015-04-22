package com.tdt4240.RawHeroes.topLayer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.input.GestureDetector;
import com.tdt4240.RawHeroes.event.move.Move;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.BoardController;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.BoardControllerReplayState;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.IBoardController;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CameraController;
import com.tdt4240.RawHeroes.gameLogic.inputListeners.QuitGameTouchDown;
import com.tdt4240.RawHeroes.gameLogic.inputListeners.TouchListenerActiveGameScreen;
import com.tdt4240.RawHeroes.gameLogic.inputListeners.EventToTranslateCamera;
import com.tdt4240.RawHeroes.gameLogic.models.StandardCamera;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.independent.MyInputProcessor;
import com.tdt4240.RawHeroes.network.client.ClientConnection;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseMessage;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

import com.tdt4240.RawHeroes.view.topLayer.GameView;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public class ActiveGameScreen extends ScreenState{
    public static final float ButtonXPos =  ((float) 7 / 8) * GameConstants.RESOLUTION_WIDTH;


    private final GameView gameView;
    private final IBoardController boardController;
    private final IBoard board;
    private final boolean iAmPlayer1;
    private final CameraController cameraController;
    private final Game game;
    private final StandardCamera camera;
    private boolean endGameState = false;
    private String message;
    private boolean addedListener = true;

    public ActiveGameScreen(ScreenStateManager gsm, Game game){
        super(gsm);
        this.game = game;
        board = game.getBoard();
        iAmPlayer1 = true;//clientConnection.getUsername().equals(game.getPlayer1Nickname());
        boardController = new BoardController(board, game.getMoveCount(), iAmPlayer1);//MUST ALWAYS BE EXECUTED BEFORE creating gameView!!

        camera = new StandardCamera();
        cameraController = new CameraController(camera);
        gameView = new GameView(board, iAmPlayer1, cameraController, boardController);

        boardController.executeMovesFromOtherPlayer(game.getLastMoves(), iAmPlayer1);
        resize(GameConstants.RESOLUTION_WIDTH, GameConstants.RESOLUTION_HEIGHT);

    }


    @Override
    public void update(float dt){
        initializeWhenViewReady();
        if(endGameState && gameView.noAnimationWaiting()) {
            endGameLogic();
        }
        gameView.update(dt);
        if(!addedListener) {
            MyInputProcessor.getInstance().AddTouchDownListener(new QuitGameTouchDown(this.message, cameraController, this));
            addedListener = true;
        }
    }
    @Override
    public void render() {
        Gdx.gl.glClearColor(0.36f, 0.32f, 0.27f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        camera.update();
        spriteBatch.setProjectionMatrix(camera.getProjectionMatrix());
        gameView.render(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        cameraController.dispose();
    }

    @Override
    public void resize(int width, int height) {
        cameraController.resize(width, height);
    }

    private void initializeTouchListeners() {
        checkIfYouHaveLost();
        GestureDetector gd = new GestureDetector(MyInputProcessor.getInstance());
        Gdx.input.setInputProcessor(gd);
        MyInputProcessor.getInstance().AddTouchDownListener(new TouchListenerActiveGameScreen(boardController, cameraController, this));
        EventToTranslateCamera cameraTranslator = new EventToTranslateCamera(cameraController);
        MyInputProcessor.getInstance().AddPanListener(cameraTranslator);
        MyInputProcessor.getInstance().AddPanStopListener(cameraTranslator);
        gameView.initializeTouchListeners(cameraController);
        boardController.actionButtonTouched();
        initializedInputListeners = true;
    }
    private void checkIfYouHaveLost() {
        game.setBoard(board);
        boolean loser = iAmPlayer1 ? game.player2IsWinner() : game.player1IsWinner();
        if(loser) {
            if(iAmPlayer1){
                message = "You lost the game against" + game.getPlayer2Nickname();}
           else{
                message = "You lost the game against" + game.getPlayer1Nickname();
           }
            finish(null);
        }
    }
    private void endGameLogic() {
        boolean winner = iAmPlayer1 ? game.player1IsWinner() : game.player2IsWinner();
        if(winner) {
            finish("Congratulations you won!");
        } else {
            finish(null);
        }
    }


    private boolean initializedInputListeners = false;
    private void initializeWhenViewReady() {
        if(!initializedInputListeners && gameView.noAnimationWaiting()) initializeTouchListeners();
    }


    public void backToMainMenu(){
        MainMenuScreen main = (MainMenuScreen) gsm.peek(0);
        main.setMsg(message);
        this.gsm.popOnly();
    }

    public void finish(String message) {
        endGameState = true;
        if(message != null) {
            this.message = message;
        }
        if(iAmPlayer1 == game.getNextTurnIsPlayer1()) {
            addedListener = false;
        }
        gameView.finishRoutine(this.message);
    }

    public void confirmTurn() {
        ArrayList<Move> moves = boardController.confirmMoves();
        ResponseMessage responseMessage = clientConnection.doMoves(game.getId(), moves);
        System.out.println(responseMessage.getType() + ", " + responseMessage.getContent());
        boardController.setState(new BoardControllerReplayState(boardController, board));
        message = (String) responseMessage.getContent();
        finish(null);

    }
    public void abortFinish() {
        gameView.abortFinish();
        MyInputProcessor.getInstance().activateListeners();
        endGameState = false;
    }
}

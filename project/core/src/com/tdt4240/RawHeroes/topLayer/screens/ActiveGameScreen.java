package com.tdt4240.RawHeroes.topLayer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.input.GestureDetector;
import com.tdt4240.RawHeroes.event.move.Move;
import com.tdt4240.RawHeroes.gameLogic.cell.CellStatus;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.BoardController;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.BoardControllerReplayState;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.BoardMover;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.IBoardController;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.IBoardMover;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CameraController;
import com.tdt4240.RawHeroes.gameLogic.inputListeners.MoveBoardTouchDraggedListener;
import com.tdt4240.RawHeroes.gameLogic.inputListeners.TouchListenerActiveGameScreen;
import com.tdt4240.RawHeroes.gameLogic.inputListeners.TranslateCamera;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.independent.MyInputProcessor;
import com.tdt4240.RawHeroes.independent.Position;
import com.tdt4240.RawHeroes.network.client.ClientConnection;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseMessage;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

import com.tdt4240.RawHeroes.topLayer.commonObjects.Games;
import com.tdt4240.RawHeroes.view.customUIElements.hudRenderer.hudRenderer;
import com.tdt4240.RawHeroes.view.topLayer.GameView;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public class ActiveGameScreen extends ScreenState{
    public static final float ButtonXPos =  ((float) 7 / 8) * GameConstants.RESOLUTION_WIDTH;


    private final GameView gameView;
    private final hudRenderer hud;
    private final BoardMover boardMover;
    private final IBoardController boardController;
    private final IBoard board;
    private final boolean iAmPlayer1;
    private final CameraController cameraController;
    private final Game game;
    private SpriteBatch hudBatch;
    private boolean endGameState = false;

    public ActiveGameScreen(ScreenStateManager gsm, Game game){
        super(gsm);
        this.game = game;
        board = game.getBoard();
        System.out.println("in active game screen!!!!!");
        iAmPlayer1 = true;
        //iAmPlayer1 = ClientConnection.getInstance().getUsername().equals(game.getPlayer1Nickname());
        if(!iAmPlayer1) {
            board.convertCellsToOtherPlayer();
        }
        boardMover = new BoardMover(board);
        boardController = new BoardController(board, boardMover, game.getMoveCount(), iAmPlayer1);//MUST ALWAYS BE EXECUTED BEFORE creating gameView!!

        cameraController = new CameraController();
        gameView = new GameView(board, iAmPlayer1, cameraController);

        hud = new hudRenderer(boardController);

        boardMover.addMoveListener(gameView);
        boardMover.addMoveListener(hud);
        board.addBoardListener(gameView);

        boardMover.executeMovesFromOtherPlayer(game.getLastMoves(), iAmPlayer1);
        hudBatch = new SpriteBatch(5);
        resize(GameConstants.RESOLUTION_WIDTH, GameConstants.RESOLUTION_HEIGHT);
    }
    private void initializeTouchListener() {
        //Check if you have lost:
        game.setBoard(board);
        boolean loser = iAmPlayer1 ? game.player2IsWinner() : game.player1IsWinner();
        if(loser) {
            MainMenuScreen main = (MainMenuScreen) gsm.peek(0);
            if(iAmPlayer1){
                main.setMsg("You lost the game against" + game.getPlayer2Nickname());}
           else{
                main.setMsg("You lost the game against" + game.getPlayer1Nickname());
            }
            ResponseMessage response = ClientConnection.getInstance().deleteGame(game.getId());
            gsm.popOnly();
        }
        GestureDetector gd = new GestureDetector(MyInputProcessor.getInstance());
        Gdx.input.setInputProcessor(gd);
        MyInputProcessor.getInstance().AddTouchDownListener(new TouchListenerActiveGameScreen(boardController, cameraController, this));
        MyInputProcessor.getInstance().AddTouchDraggedListener(new MoveBoardTouchDraggedListener(cameraController));
        MyInputProcessor.getInstance().AddFlingListener(new TranslateCamera(cameraController));
        gameView.initializeTouchListeners(cameraController);
        initialized = true;
    }

    @Override
    public void update(float dt) {
        gameView.update(dt);
    }

    @Override
    public void render() {
        initializeWhenViewReady();
        if(endGameState && gameView.noAnimationWaiting()) {
            boolean winner = iAmPlayer1 ? game.player1IsWinner() : game.player2IsWinner();
            System.out.println("About to finish game");
            gsm.popOnly();
            if(winner) {
                //TODO: PostgameScreen

            } else {
            }
        }
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
        cameraController.dispose();
    }

    @Override
    public void resize(int width, int height) {
        cameraController.resize(width, height);
    }


    public void backToMainMenu(){
        this.gsm.popOnly();
    }

    public void confirmTurn() {
        ArrayList<Move> moves = boardMover.confirmMoves();
        //ResponseMessage responseMessage = clientConnection.doMoves(game.getId(), moves);
        //System.out.println(responseMessage.getType() + ", " + responseMessage.getContent());
        boardController.setState(new BoardControllerReplayState(boardController, board));
        //MainMenuScreen main = (MainMenuScreen) gsm.peek(0);
        //main.setMsg((String) responseMessage.getContent());
        endGameState = true;

    }
}

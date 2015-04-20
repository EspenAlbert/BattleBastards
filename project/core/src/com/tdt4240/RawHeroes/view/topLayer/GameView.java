package com.tdt4240.RawHeroes.view.topLayer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.tdt4240.RawHeroes.event.events.BoardResetEvent;
import com.tdt4240.RawHeroes.event.listener.IBoardListener;
import com.tdt4240.RawHeroes.event.events.CellChangeEvent;
import com.tdt4240.RawHeroes.event.listener.ICameraListener;
import com.tdt4240.RawHeroes.event.listener.IMoveListener;
import com.tdt4240.RawHeroes.event.move.Move;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CameraController;
import com.tdt4240.RawHeroes.gameLogic.inputListeners.ActivateUnitDetails;
import com.tdt4240.RawHeroes.gameLogic.inputListeners.TouchDownRemoveUnitDetails;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.ICameraController;
import com.tdt4240.RawHeroes.event.events.BoardEvent;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.independent.MyInputProcessor;
import com.tdt4240.RawHeroes.view.customUIElements.boardRenderer.BoardRenderer;
import com.tdt4240.RawHeroes.view.customUIElements.unitDetailRenderer.UnitDetailRenderer;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.UnitRenderer;
import com.tdt4240.RawHeroes.view.uiElements.DialogFactory;

/**
 * Created by espen1 on 27.02.2015.
 */
public class GameView implements IView, IBoardListener, ICameraListener, IMoveListener {

    public  String endMessage = "THIS MESSAGE SHOULD BE DISPLAYED";
    private final UnitRenderer unitRenderer;
    private final BoardRenderer boardRenderer;
    private final IBoard board;
    private final UnitDetailRenderer unitDetails;
    private BitmapFont font;


    public GameView(IBoard board, boolean iAmPlayer1, ICameraController camera) {
        this.board = board;
        boardRenderer = new BoardRenderer(board, iAmPlayer1);
        unitRenderer = new UnitRenderer(board, camera, iAmPlayer1);
        unitDetails = new UnitDetailRenderer(board);
        finishRoutine();
    }

    public void BoardChanged(BoardEvent event) {
        if (event instanceof CellChangeEvent) {
            // change button for cell
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        boardRenderer.render(batch);
        unitRenderer.render(batch);
        unitDetails.render(batch);
        if(font != null) {
            font.draw(batch, endMessage, GameConstants.RESOLUTION_WIDTH / 2, GameConstants.RESOLUTION_HEIGHT / 2);
            font.draw(batch, endMessage, 0,100);
            //TODO: Make the text visible...
            //System.out.println("Drawing text...");
        }
    }

    @Override
    public void boardChanged(BoardEvent event) {
        if(event instanceof BoardResetEvent) {
            unitRenderer.setupUnitRenderer();
        }
    }

    @Override
    public void cameraShifted(int dx, int dy) {

    }

    @Override
    public void moveExecuted(Move move) {
        unitRenderer.moveExecuted(move);
    }

    public boolean noAnimationWaiting() {
        return unitRenderer.noAnimationWaiting();
    }

    public void initializeTouchListeners(CameraController camera){
        MyInputProcessor.getInstance().AddLongListener(new ActivateUnitDetails(unitDetails, camera));
        MyInputProcessor.getInstance().AddTouchDownListener(new TouchDownRemoveUnitDetails(unitDetails));
    }

    public void finishRoutine() {
        font = new BitmapFont();
        font.scale(500);
        font.setColor(Color.BLUE);
    }
}

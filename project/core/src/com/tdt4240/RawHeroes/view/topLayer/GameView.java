package com.tdt4240.RawHeroes.view.topLayer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.tdt4240.RawHeroes.event.events.BoardResetEvent;
import com.tdt4240.RawHeroes.event.listener.IBoardListener;
import com.tdt4240.RawHeroes.event.events.CellChangeEvent;
import com.tdt4240.RawHeroes.event.listener.ICameraListener;
import com.tdt4240.RawHeroes.event.listener.IMoveListener;
import com.tdt4240.RawHeroes.event.move.Move;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.IBoardMover;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CameraController;
import com.tdt4240.RawHeroes.gameLogic.inputListeners.ActivateUnitDetails;
import com.tdt4240.RawHeroes.gameLogic.inputListeners.TouchDownRemoveUnitDetails;
import com.tdt4240.RawHeroes.gameLogic.models.ICamera;
import com.tdt4240.RawHeroes.event.events.BoardEvent;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.independent.MyInputProcessor;
import com.tdt4240.RawHeroes.independent.Position;
import com.tdt4240.RawHeroes.view.customUIElements.boardRenderer.BoardRenderer;
import com.tdt4240.RawHeroes.view.customUIElements.unitDetailRenderer.UnitDetailRenderer;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.UnitRenderer;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public class GameView implements IView, IBoardListener, ICameraListener, IMoveListener {

    private final UnitRenderer unitRenderer;
    private final BoardRenderer boardRenderer;
    private final IBoard board;
    private final UnitDetailRenderer unitDetails;


    public GameView(IBoard board, boolean iAmPlayer1, ICamera camera) {
        this.board = board;
        boardRenderer = new BoardRenderer(board, iAmPlayer1);
        unitRenderer = new UnitRenderer(board, camera, iAmPlayer1);
        unitDetails = new UnitDetailRenderer(board);
    }

    public void BoardChanged(BoardEvent event) {
        if (event instanceof CellChangeEvent) {
            // change button for cell
        }
    }

    public void update(float dt){
        unitRenderer.update(dt);
    }

    @Override
    public void render(SpriteBatch batch) {
        boardRenderer.render(batch);
        unitRenderer.render(batch);
        unitDetails.render(batch);
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
}

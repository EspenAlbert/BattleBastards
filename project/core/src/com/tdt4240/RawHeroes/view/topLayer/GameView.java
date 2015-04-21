package com.tdt4240.RawHeroes.view.topLayer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tdt4240.RawHeroes.event.events.BoardResetEvent;
import com.tdt4240.RawHeroes.event.listener.IBoardListener;
import com.tdt4240.RawHeroes.event.listener.IMoveListener;
import com.tdt4240.RawHeroes.event.move.Move;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.IBoardController;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CameraController;
import com.tdt4240.RawHeroes.gameLogic.inputListeners.ActivateUnitDetails;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.ICameraController;
import com.tdt4240.RawHeroes.event.events.BoardEvent;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.independent.MyInputProcessor;
import com.tdt4240.RawHeroes.view.customUIElements.boardRenderer.BoardRenderer;
import com.tdt4240.RawHeroes.view.customUIElements.finishScreenRenderer.FinishScreenRenderer;
import com.tdt4240.RawHeroes.view.customUIElements.hudRenderer.HudRenderer;
import com.tdt4240.RawHeroes.view.customUIElements.unitDetailRenderer.UnitDetailRenderer;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.UnitRenderer;

/**
 * Created by espen1 on 27.02.2015.
 */
public class GameView implements IView, IBoardListener, IMoveListener {

    private SpriteBatch hudBatch;
    private FinishScreenRenderer finishScreenRenderer;
    private final UnitRenderer unitRenderer;
    private final BoardRenderer boardRenderer;
    private final UnitDetailRenderer unitDetails;
    private final HudRenderer hudRenderer;


    public GameView(IBoard board, boolean iAmPlayer1, ICameraController camera, IBoardController boardController) {
        boardRenderer = new BoardRenderer(board, iAmPlayer1);
        unitRenderer = new UnitRenderer(board, camera, iAmPlayer1);
        unitDetails = new UnitDetailRenderer(board);
        hudRenderer = new HudRenderer(boardController);
        hudBatch = new SpriteBatch(5);
        finishScreenRenderer = null;
        boardController.addMoveListener(this);
        board.addBoardListener(this);
    }

    public void update(float dt){
        unitRenderer.update(dt);
    }

    @Override
    public void render(SpriteBatch batch) {
        boardRenderer.render(batch);
        unitRenderer.render(batch);
        unitDetails.render(batch);

        if(finishScreenRenderer != null && unitRenderer.noAnimationWaiting()) {
            finishScreenRenderer.render(batch);
        }

        hudRenderer.render(hudBatch);
    }

    @Override
    public void boardChanged(BoardEvent event) {
        if(event instanceof BoardResetEvent) {
            unitRenderer.setupUnitRenderer();
        }
    }


    @Override
    public void moveExecuted(Move move) {
        hudRenderer.moveExecuted(move);
        unitRenderer.moveExecuted(move);
    }

    public boolean noAnimationWaiting() {
        return unitRenderer.noAnimationWaiting();
    }

    public void initializeTouchListeners(CameraController camera){
        MyInputProcessor.getInstance().AddLongListener(new ActivateUnitDetails(unitDetails, camera));
    }

    public void finishRoutine(String message) {
        finishScreenRenderer = new FinishScreenRenderer(message);
    }

    public void abortFinish() {
        finishScreenRenderer = null;
    }
}

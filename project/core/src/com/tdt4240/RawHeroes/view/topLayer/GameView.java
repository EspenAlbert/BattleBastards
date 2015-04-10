package com.tdt4240.RawHeroes.view.topLayer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.tdt4240.RawHeroes.event.events.AttackEvent;
import com.tdt4240.RawHeroes.event.listener.IBoardListener;
import com.tdt4240.RawHeroes.event.events.CellChangeEvent;
import com.tdt4240.RawHeroes.event.listener.ICameraListener;
import com.tdt4240.RawHeroes.gameLogic.models.ICamera;
import com.tdt4240.RawHeroes.event.events.BoardEvent;
import com.tdt4240.RawHeroes.event.events.MovementEvent;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.view.customUIElements.boardRenderer.BoardRenderer;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.UnitRenderer;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public class GameView implements IView, IBoardListener, ICameraListener {

    private final UnitRenderer unitRenderer;
    private final BoardRenderer boardRenderer;


    private ArrayList<ArrayList<Button>> buttons;

    private int cameraY;
    private int cameraX;

    public GameView(IBoard board, boolean iAmPlayer1, ICamera camera) {
        boardRenderer = new BoardRenderer(board, iAmPlayer1);
        unitRenderer = new UnitRenderer(board, camera, iAmPlayer1);


    }

    public void BoardChanged(BoardEvent event) {
        if (event instanceof CellChangeEvent) {
            // change button for cell
        } else if (event instanceof AttackEvent) {

        } else if (event instanceof MovementEvent) {

        }
    }

    @Override
    public void render(SpriteBatch batch) {
        unitRenderer.render(batch);
        boardRenderer.render(batch, null);
    }

    @Override
    public void boardChanged(BoardEvent event) {

    }

    @Override
    public void cameraShifted(int dx, int dy) {

    }
}

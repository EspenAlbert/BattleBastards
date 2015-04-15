package com.tdt4240.RawHeroes.view.customUIElements.unitRenderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.models.ICamera;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.ICellConverter;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.Player1CellConverter;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.Player2CellConverter;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.event.move.AttackMove;
import com.tdt4240.RawHeroes.event.listener.IMoveListener;
import com.tdt4240.RawHeroes.event.move.Move;
import com.tdt4240.RawHeroes.event.move.MovementMove;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.IRenderBulding;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.IRenderObject;
import com.tdt4240.RawHeroes.event.listener.ICameraListener;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.RenderBuilding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by espen1 on 27.02.2015.
 */
public class UnitRenderer implements IMoveListener, ICameraListener {
    private final IBoard board;
    private ICamera camera;

    private IRenderBulding renderBulding = RenderBuilding.getInstance();
    private HashMap<Vector2, IRenderObject> unitPositionsAndRenderObjects;
    private Queue<Move> currentAnimations = new LinkedList<Move>();//TODO: Continue
    private boolean animationActive = false;

    public UnitRenderer(IBoard board, ICamera camera, boolean iAmPlayer1) {
        this.board = board;
        this.camera = camera;

        ICellConverter cellConverter = iAmPlayer1 ? new Player1CellConverter() : new Player2CellConverter();

        ICell[][] cells = cellConverter.convertCells(board.getCells());
        ArrayList<Vector2> unitPositions = new ArrayList<Vector2>();
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[0].length; y++) {
                if (cells[x][y].getUnit() != null) {
                    unitPositions.add(new Vector2(x, y));
                }
            }
        }
        unitPositionsAndRenderObjects = new HashMap<Vector2, IRenderObject>();
        for (Vector2 pos : unitPositions) {
            unitPositionsAndRenderObjects.put(pos, renderBulding.getRenderObject(board.getCell(pos).getUnit()));
        }
    }


    @Override
    public void moveExecuted(Move move) {
       if(move instanceof MovementMove) {
           MovementMove movement = null;
          // Vector2 moves = move.getStartCell()
       }

       //currentAnimations.add(move);
    }

    private void movementMove(MovementMove move) {
        /*
        1. Set unit renderMode = moving
        2. AnimationQueue.add(new MovementAnimation(move))
         */
    }

    public void render(SpriteBatch batch) {
        for(Vector2 key : unitPositionsAndRenderObjects.keySet()) {
            unitPositionsAndRenderObjects.get(key).render(batch, key);
        }
        /*
        if(!animationActive && !currentAnimations.isEmpty()) {
            executeMove(currentAnimations.poll());
        }
        */
    }

    private void executeMove(Move move) {
        animationActive = true;
        if (move instanceof AttackMove) {
            //attackMoveExecuted((AttackMove) move);
        }
        else if(move instanceof MovementMove) {
            movementMove((MovementMove) move);
        }
    }
    @Override
    public void cameraShifted(int dx, int dy) {

    }
}

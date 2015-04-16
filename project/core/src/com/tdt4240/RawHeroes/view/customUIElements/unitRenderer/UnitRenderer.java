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
public class UnitRenderer implements IMoveListener {
    private final UnitMoveExecutor moveExecutor;
    private ICamera camera;

    private IRenderBulding renderBulding = RenderBuilding.getInstance();
    private HashMap<Vector2, IRenderObject> unitPositionsAndRenderObjects;
    private Queue<Move> currentAnimations = new LinkedList<Move>();//TODO: Continue
    private boolean animationIsActive = false;

    public UnitRenderer(IBoard board, ICamera camera, boolean iAmPlayer1) {
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
        moveExecutor = new UnitMoveExecutor(this);
    }


    @Override
    public void moveExecuted(Move move) {
        currentAnimations.add(move);
    }

    private void executeMove(Move move) {
        animationIsActive = true;
        Vector2 startPos = move.getStartCell().getPos();
        Vector2 endPos = move.getTargetCell().getPos();
        camera.makeSureVisible(startPos, endPos);

        if (move instanceof AttackMove) {
            IRenderObject attacker = unitPositionsAndRenderObjects.get(startPos);
            ArrayList<IRenderObject> victims = getVictims((AttackMove) move);
            moveExecutor.attackMove(attacker, victims);
        }
        else if(move instanceof MovementMove) {
            IRenderObject mover = unitPositionsAndRenderObjects.get(startPos);
            unitPositionsAndRenderObjects.remove(startPos);
            moveExecutor.movementMove(mover, (MovementMove) move);
        }
        /*
        if(move instanceof MovementMove) {
            MovementMove movement = (MovementMove) move;
            Vector2 mover = movement.getStartCell().getPos();
            Vector2 endCell = movement.getTarget();
            IRenderObject renderer = unitPositionsAndRenderObjects.get(mover);
            System.out.println("This should not be null! : " + renderer);
            unitPositionsAndRenderObjects.remove(mover);
            unitPositionsAndRenderObjects.put(endCell, renderer);
        }
        */
    }

    private ArrayList<IRenderObject> getVictims(AttackMove move) {
        ArrayList<ICell> victims = move.getVictims();
        ArrayList<IRenderObject> victimsRenderObjects = new ArrayList<IRenderObject>();
        if(victims != null) {
            for(ICell cell : victims){
                victimsRenderObjects.add(unitPositionsAndRenderObjects.get(cell.getPos()));
            }
        }
        return victimsRenderObjects;
    }


    public void render(SpriteBatch batch) {
        if(animationIsActive) moveExecutor.update(batch);

        for(Vector2 key : unitPositionsAndRenderObjects.keySet()) {
            unitPositionsAndRenderObjects.get(key).render(batch, key);
        }


        if(!animationIsActive && !currentAnimations.isEmpty()) {
            executeMove(currentAnimations.poll());
        }
    }

    public boolean noAnimationWaiting() {
        return currentAnimations.size() > 0 && !animationIsActive;
    }

    public void movementMoveComplete(IRenderObject currentActor, Vector2 endPos) {
        unitPositionsAndRenderObjects.put(endPos, currentActor);
        animationIsActive = false;
    }

    public void attackMoveFinished() {
        animationIsActive = false;
    }
}

package com.tdt4240.RawHeroes.view.customUIElements.unitRenderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.event.events.AnimationEvent;
import com.tdt4240.RawHeroes.event.listener.IAnimationListener;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.models.ICamera;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.event.move.AttackMove;
import com.tdt4240.RawHeroes.event.listener.IMoveListener;
import com.tdt4240.RawHeroes.event.move.Move;
import com.tdt4240.RawHeroes.event.move.MovementMove;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.independent.AnimationConstants;
import com.tdt4240.RawHeroes.independent.Position;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.IRenderBulding;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.IRenderObject;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.RenderBuilding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Created by espen1 on 27.02.2015.
 */
public class UnitRenderer implements IMoveListener {
    private UnitMoveExecutor moveExecutor;
    private IBoard board;
    private ICamera camera;

    private ArrayList<IUnit> units;

    private IRenderBulding renderBuilding = RenderBuilding.getInstance();
    private HashMap<Position, IRenderObject> unitPositionsAndRenderObjects;
    private Queue<Move> currentAnimations = new LinkedList<Move>();
    private boolean animationIsActive = false;


    public UnitRenderer(IBoard board, ICamera camera, boolean iAmPlayer1) {
        this.board = board;
        this.camera = camera;
        this.units = new ArrayList<IUnit>();
        setupUnitRenderer();
    }

    public void setupUnitRenderer() {
        currentAnimations.clear();
        animationIsActive = false;
        ArrayList<Position> unitPositions = board.getUnitPositions();
        unitPositionsAndRenderObjects = new HashMap<Position, IRenderObject>();
        for (Position pos : unitPositions) {
            IUnit unit = board.getCell(pos).getUnit();
            units.add(unit);
            IRenderObject renderObject = renderBuilding.getRenderObject(unit);
            unitPositionsAndRenderObjects.put(pos, renderObject);
            unit.addAnimationListener(renderObject);
        }
        moveExecutor = new UnitMoveExecutor(this);
    }


    @Override
    public void moveExecuted(Move move) {
        currentAnimations.add(move);
    }

    private void executeMove(Move move) {
        animationIsActive = true;
        Position startPos = move.getStartCell().getPos();
        Position endPos = move.getTargetCell().getPos();
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
    }

    private ArrayList<IRenderObject> getVictims(AttackMove move) {
        Iterable<Position> victims = move.getVictims();
        ArrayList<IRenderObject> victimsRenderObjects = new ArrayList<IRenderObject>();
        if(victims != null) {
            for(Position pos : victims){
                if(unitPositionsAndRenderObjects.containsKey(pos)) {
                    victimsRenderObjects.add(unitPositionsAndRenderObjects.get(pos));
                }
            }
        }
        return victimsRenderObjects;
    }

    public void update(float dt){
        AnimationConstants.timer += dt*1000;
        if (AnimationConstants.timer > AnimationConstants.FRAME_TIME){
            AnimationConstants.timer = 0;
            for (IUnit unit : this.units){
                unit.nextFrame();
            }
        }
    }
    public void render(SpriteBatch batch) {
        boolean hasRenderedActor = false;
        ArrayList<Position> keys = new ArrayList<Position>(unitPositionsAndRenderObjects.keySet());
        Collections.sort(keys);
        for(Position key : keys) {
            if(animationIsActive) {
                Vector2 actorPos = moveExecutor.getCurrentPos();
                if (!hasRenderedActor && actorPos.y > key.getY()) {
                    moveExecutor.update(batch);
                    hasRenderedActor = true;
                }
            }
            unitPositionsAndRenderObjects.get(key).render(batch, key.getVec2Pos());
        }
        if(!hasRenderedActor && animationIsActive)moveExecutor.update(batch);

        if(!animationIsActive && !currentAnimations.isEmpty()) {
            executeMove(currentAnimations.poll());
        }
    }

    public boolean noAnimationWaiting() {
        return currentAnimations.size() < 1 && !animationIsActive;
    }

    public void movementMoveComplete(IRenderObject currentActor, Position endPos) {
        unitPositionsAndRenderObjects.put(endPos, currentActor);
        animationIsActive = false;
    }

    public void attackMoveFinished() {
        animationIsActive = false;
    }
}

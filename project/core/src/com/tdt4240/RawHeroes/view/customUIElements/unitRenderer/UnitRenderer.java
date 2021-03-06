package com.tdt4240.RawHeroes.view.customUIElements.unitRenderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.createUnits.factory.UnitFactory;
import com.tdt4240.RawHeroes.event.move.SkipMove;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.ICameraController;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.event.move.AttackMove;
import com.tdt4240.RawHeroes.event.listener.IMoveListener;
import com.tdt4240.RawHeroes.event.move.Move;
import com.tdt4240.RawHeroes.event.move.MovementMove;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.gameLogic.models.UnitRenderModel;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;
import com.tdt4240.RawHeroes.independent.AnimationConstants;
import com.tdt4240.RawHeroes.independent.Position;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.IRenderObject;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.renderObjects.RenderObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by espen1 on 27.02.2015.
 */
public class UnitRenderer implements IMoveListener {
    private UnitMoveExecutor moveExecutor;
    private IBoard board;
    private ICameraController cameraController;
    private ArrayList<UnitRenderModel> renderModels;

    private HashMap<Position, IRenderObject> unitPositionsAndRenderObjects;
    private Queue<Move> currentAnimations = new LinkedList<Move>();
    private boolean animationIsActive = false;
    private boolean iAmPlayer1;

    public UnitRenderer(IBoard board, ICameraController cameraController, boolean iAmPlayer1) {
        this.board = board;
        this.iAmPlayer1 = iAmPlayer1;
        this.cameraController = cameraController;
        this.iAmPlayer1 = iAmPlayer1;
        this.renderModels = new ArrayList<UnitRenderModel>();
        setupUnitRenderer();
    }

    public void setupUnitRenderer() {
        currentAnimations.clear();
        animationIsActive = false;
        ArrayList<Position> unitPositions = board.getUnitPositions();
        unitPositionsAndRenderObjects = new HashMap<Position, IRenderObject>();
        for (Position pos : unitPositions) {
            IUnit unit = board.getCell(pos).getUnit();
            if(unit.getHealth() < 1) continue;
            UnitRenderModel renderModel = new UnitRenderModel(unit, iAmPlayer1);
            renderModels.add(renderModel);
            IRenderObject renderObject = new RenderObject(renderModel, renderModel.getUnitAnimationController());
            unitPositionsAndRenderObjects.put(pos, renderObject);
            renderModel.addAnimationListener(renderObject);
        }
        //TODO må finne en bedre løsning. hvorfor fungerer dette?
        UnitRenderModel dummyModel = new UnitRenderModel(UnitFactory.getInstance().createUnit(UnitName.STANDARD_UNIT, true), true);
        unitPositionsAndRenderObjects.put(new Position(-1, -1),new RenderObject(dummyModel, dummyModel.getUnitAnimationController()));

        moveExecutor = new UnitMoveExecutor(this);
    }




    public void update(float dt){
        AnimationConstants.timer += dt*1000;
        if (AnimationConstants.timer > AnimationConstants.FRAME_TIME){
            AnimationConstants.timer = 0;
            for (UnitRenderModel renderModel : this.renderModels){
                renderModel.nextFrame();
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

    @Override
    public void moveExecuted(Move move) {
        if(move instanceof SkipMove){
            currentAnimations.clear();
        }
        currentAnimations.add(move);
    }

    private void executeMove(Move move) {
        animationIsActive = true;
        Position startPos = move.getStartCell().getPos();
        Position endPos = move.getTargetCell().getPos();
        cameraController.makeSureVisible(startPos, endPos);
        if(unitPositionsAndRenderObjects.get(startPos) == null) return;//Probably means the unit has allready been killed
        if (endPos.getX() < startPos.getPos().getX() && unitPositionsAndRenderObjects.get(startPos).getRenderModel().isTurnedRight()){
            unitPositionsAndRenderObjects.get(startPos).getRenderModel().turnDirection();
        }else if (endPos.getX() >= startPos.getX() && !unitPositionsAndRenderObjects.get(startPos).getRenderModel().isTurnedRight())
            unitPositionsAndRenderObjects.get(startPos).getRenderModel().turnDirection();


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
}

package com.tdt4240.RawHeroes.view.customUIElements.unitRenderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.event.move.MovementMove;
import com.tdt4240.RawHeroes.independent.Position;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.IRenderObject;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.RenderMode;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.renderObject1.ActualRenderObject;

import java.util.ArrayList;

/**
 * Created by espen1 on 12.03.2015.
 */
public class UnitMoveExecutor {

    private final UnitRenderer unitRenderer;
    private boolean currentAnimationIsAttack;
    private Vector2 direction;
    private float speed;
    private Vector2 currentPos;
    private int movementSteps;
    private ArrayList<Position> path;
    private IRenderObject currentActor;
    private int currentIndex;
    private ArrayList<IRenderObject> victims;

    public UnitMoveExecutor(UnitRenderer unitRenderer) {
        this.unitRenderer = unitRenderer;
        speed = 0.05f;
    }


    public void movementMove(IRenderObject mover, MovementMove move) {
        path = move.getPath();
        currentIndex = 0;
        currentActor = mover;
        currentPos = move.getStartCell().getPos().getVec2Pos();
        currentActor.changeRenderMode(RenderMode.MOVING);
        currentAnimationIsAttack = false;

    }

    public void attackMove(IRenderObject attacker, ArrayList<IRenderObject> victims) {
        currentAnimationIsAttack = true;
        currentActor = attacker;
        currentActor.changeRenderMode(RenderMode.ATTACKING);
        this.victims = victims;
        for(IRenderObject victim: victims) {
            victim.changeRenderMode(RenderMode.HURT);
        }
    }

    public void update(SpriteBatch batch) {
        if(currentAnimationIsAttack) {
            if(attackIsComplete()) finishAttackMove();
        }
        else {
            move();
            if(movementIsComplete()) {
                finishMovementMove();
            }
            currentActor.render(batch, currentPos);
        }
    }

    private boolean attackIsComplete() {
        if(currentActor.getRenderMode() == RenderMode.ATTACKING) return false;
        for(IRenderObject victim: victims) {
            if(victim.getRenderMode() == RenderMode.HURT) {
                return false;
            }
        }
        return true;
    }

    private void finishMovementMove() {
        currentActor.changeRenderMode(RenderMode.STATIC);
        unitRenderer.movementMoveComplete(currentActor, path.get(currentIndex-1));
    }

    private void finishAttackMove() {
        unitRenderer.attackMoveFinished();

    }

    private void move() {
        direction = path.get(currentIndex).cpy().getVec2Pos().sub(currentPos);
        Vector2 deltaMovement = direction.setLength(1).scl(speed);
        currentPos.add(deltaMovement);
        if(positionsIsAlmostTheSame(path.get(currentIndex).getVec2Pos(), currentPos)) currentIndex++;//This check needs to be improved in case that  the coordinate isn't exactly the same
    }

    private boolean positionsIsAlmostTheSame(Vector2 a, Vector2 b) {
        float margin = 0.05f;
        float difference = Math.abs((a.x-b.x) + (a.y - b.y));

        return difference < margin;
    }

    public Vector2 getCurrentPos(){
        return this.currentPos;
    }

    private boolean movementIsComplete() {
        if(currentIndex==path.size()&&currentActor instanceof ActualRenderObject) {
            ((ActualRenderObject) currentActor).setWalkingFalse();
        }
        return currentIndex == path.size();
    }
}

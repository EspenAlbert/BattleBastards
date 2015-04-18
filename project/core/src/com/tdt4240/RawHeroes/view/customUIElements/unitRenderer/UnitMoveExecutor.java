package com.tdt4240.RawHeroes.view.customUIElements.unitRenderer;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.event.move.AttackMove;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.IRenderObject;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.RenderMode;

import java.util.ArrayList;

/**
 * Created by espen1 on 12.03.2015.
 */
public class UnitMoveExecutor {

    private boolean currentAnimationIsAttack;
    private IRenderObject renderAttacker;
    private IRenderObject renderMover;
    private Vector2 direction;
    private int speed;
    private int movementSteps;

    public boolean readyForNextAnimation() {
        if(currentAnimationIsAttack) {
            return renderAttacker.getRenderMode() == RenderMode.ATTACKING;
        } else {
            if(movementSteps < 1) return true;
         //   renderMover.changePos(direction.scl(speed));
            movementSteps --;

            if(movementSteps < 1) renderMover.changeRenderMode(RenderMode.STATIC);
        }
        return false;
    }

    public void attackMoveExecuted(AttackMove move) {
        currentAnimationIsAttack = true;
        Vector2 attackerPos = move.getStartCell().getPos();
   //     renderAttacker = unitPositionsAndRenderObjects.get(attackerPos);
        renderAttacker.changeRenderMode(RenderMode.ATTACKING);
        //TODO: Fix this.... getVictims...
        /*
        ArrayList<ICell> victims = move.getVictims();
        for (ICell cell : victims) {
            if (cell.getUnit().getHealth() > 0) {
      //          unitPositionsAndRenderObjects.get(cell.getPos()).changeRenderMode(RenderMode.HURT);
            } else {
       //         unitPositionsAndRenderObjects.get(cell.getPos()).changeRenderMode(RenderMode.KILLED);
            }
        }
        */
    }
}

package com.tdt4240.RawHeroes.render.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.action.IActionListener;
import com.tdt4240.RawHeroes.logic.cell.ICell;
import com.tdt4240.RawHeroes.logic.model.IBoard;
import com.tdt4240.RawHeroes.logic.move.AttackMove;
import com.tdt4240.RawHeroes.logic.move.IMoveListener;
import com.tdt4240.RawHeroes.logic.move.Move;
import com.tdt4240.RawHeroes.logic.move.MovementMove;
import com.tdt4240.RawHeroes.render.topLayer.IRender;
import com.tdt4240.RawHeroes.render.topLayer.IRenderBulding;
import com.tdt4240.RawHeroes.render.topLayer.IRenderObject;
import com.tdt4240.RawHeroes.render.topLayer.RenderBuilding;
import com.tdt4240.RawHeroes.render.topLayer.RenderMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by espen1 on 27.02.2015.
 */
public class UnitRenderer implements IRender, IMoveListener, IActionListener, ICameraListener {
    private final IBoard board;
    private IBoardView view;

    private HashMap<Vector2, IRenderObject> unitPositionsAndAnimations;
    private IRenderBulding renderBulding = RenderBuilding.getInstance();
    private Queue<Move> currentAnimations2 = new LinkedList<Move>();//TODO: Continue
    private ArrayList<IRenderObject> currentAnimations;

    public UnitRenderer(ArrayList<Vector2> unitPositions, IBoard board, IBoardView view) {
        this.board = board;
        this.view = view;
        unitPositionsAndAnimations = new HashMap<Vector2, IRenderObject>();
        for(Vector2 pos: unitPositions) {
            unitPositionsAndAnimations.put(pos, renderBulding.getStillRender(board.getCell(pos).getUnit()));
        }
        currentAnimations = new ArrayList<IRenderObject>();

    }


    public void attackMoveExecuted(AttackMove move) {
        Vector2 attackerPos = move.getStartCell().getPos();
        unitPositionsAndAnimations.get(attackerPos).changeRenderMode(RenderMode.ATTACKING);
        ArrayList<ICell> victims = move.getVictims();
        for(ICell cell : victims) {
            if(cell.getUnit().getHealth() > 0) {
                unitPositionsAndAnimations.get(cell.getPos()).changeRenderMode(RenderMode.HURT);
            } else {
                unitPositionsAndAnimations.get(cell.getPos()).changeRenderMode(RenderMode.KILLED);
            }
        }
        currentAnimations.add(renderBulding.getAnimationAttackRender(move));

    }
    @Override
    public void moveExecuted(Move move) {
        if (move instanceof AttackMove) {
            attackMoveExecuted((AttackMove) move);
        }
        else if(move instanceof MovementMove) {
            movementMove((MovementMove) move);
        }
        //TODO: Implement animation
    }

    private void movementMove(MovementMove move) {
        /*
        1. Set unit renderMode = moving
        2. AnimationQueue.add(new MovementAnimation(move))
         */
    }

    public void render(SpriteBatch batch) {

    }

    @Override
    public void render(SpriteBatch batch, Vector2 pos) {
        for(Vector2 key : unitPositionsAndAnimations.keySet()) {
            unitPositionsAndAnimations.get(key).render(batch, key);
        }
    }

    @Override
    public void cameraShifted(int dx, int dy) {

    }
}

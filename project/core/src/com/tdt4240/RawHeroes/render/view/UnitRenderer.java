package com.tdt4240.RawHeroes.render.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.logic.model.IBoard;
import com.tdt4240.RawHeroes.logic.move.AttackMove;
import com.tdt4240.RawHeroes.logic.move.IMoveListener;
import com.tdt4240.RawHeroes.logic.move.Move;
import com.tdt4240.RawHeroes.render.topLayer.IRenderBulding;
import com.tdt4240.RawHeroes.render.topLayer.IRenderObject;
import com.tdt4240.RawHeroes.render.topLayer.RenderBuilding;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by espen1 on 27.02.2015.
 */
public class UnitRenderer implements IRenderObject, IMoveListener {
    private final IBoard board;
    private HashMap<Vector2, IRenderObject> unitPositionsAndAnimations;
    private IRenderBulding renderBulding = RenderBuilding.getInstance();
    private ArrayList<IRenderObject> currentAnimations;

    public UnitRenderer(ArrayList<Vector2> unitPositions, IBoard board) {
        this.board = board;
        unitPositionsAndAnimations = new HashMap<Vector2, IRenderObject>();
        for(Vector2 pos: unitPositions) {
            unitPositionsAndAnimations.put(pos, renderBulding.getStillRender(board.getCell(pos).getUnit()));
        }
        currentAnimations = new ArrayList<>();

    }


    public void attackMoveExecuted(AttackMove move) {
        currentAnimations.add(renderBulding.getAnimationAttackRender(move));

    }
    @Override
    public void moveExecuted(Move move) {
        if (move instanceof AttackMove) {
            attackMoveExecuted((AttackMove) move);
        }
        //TODO: Implement animation
    }

    public void render(SpriteBatch batch) {

    }
}

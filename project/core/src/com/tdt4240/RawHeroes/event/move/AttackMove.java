package com.tdt4240.RawHeroes.event.move;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by espen1 on 27.02.2015.
 */
public class AttackMove extends Move {

    Vector2 attackerPos, targetPos;
    HashMap<Vector2, Integer> damages;

    private ArrayList<ICell> victims;

    public AttackMove(ICell selectedCell, ICell target) {
        super(selectedCell);
        this.damages = new HashMap<Vector2, Integer>();
        this.attackerPos = selectedCell.getPos();
        this.targetPos = target.getPos();
    }

    public void execute(IBoard board) {
        if (damages != null) {
            for (Vector2 key : damages.keySet()) {
                board.getCell(key).getUnit().attacked(damages.get(key));
            }
        }
        getDamages(board);
    }

    private void getDamages(IBoard board) {
        IUnit attacker = board.getCell(attackerPos).getUnit();
        ArrayList<Vector2> inflictionZone = attacker.getInflictionZone(attackerPos, targetPos);
        for (Vector2 victimPos : inflictionZone) {
            IUnit victim = board.getCell(victimPos).getUnit();
            if (victim != null) {
                int dmg = attacker.inflictDamage(attackerPos, targetPos);
                victim.attacked(dmg);
                damages.put(victimPos, dmg);
            }
        }
    }
}
package com.tdt4240.RawHeroes.logic.events;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.logic.move.AttackMove;

/**
 * Created by espen1 on 27.02.2015.
 */
public class AttackEvent extends BoardEvent {
    private final AttackMove move;

    public AttackEvent(Vector2 posision, AttackMove move) {
        super(posision);
        this.move = move;
    }

    public AttackMove getMove() {
        return move;
    }
}

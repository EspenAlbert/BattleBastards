package com.tdt4240.RawHeroes.gameLogic.controllers.unitController;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;

import java.util.ArrayList;

/**
 * Created by espen1 on 12.04.2015.
 */
public class SimpleUnitAttackController implements IUnitAttackController {

    private IUnit unit;
    private int attackDmg, armor;

    public SimpleUnitAttackController(IUnit u, int attackDmg, int armor){
        this.unit = u;
        this.attackDmg = attackDmg;
        this.armor = armor;
    }

    @Override
    public ArrayList<Vector2> getAttackablePositions(Vector2 pos, int movesLeft) {
        ArrayList<Vector2> temp = new ArrayList<Vector2>();
        temp.add(new Vector2(pos.x+1, pos.y));
        temp.add(new Vector2(pos.x-1, pos.y));
        temp.add(new Vector2(pos.x, pos.y+1));
        temp.add(new Vector2(pos.x, pos.y-1));
        return temp;
    }

    @Override
    public ArrayList<Vector2> getInflictionZone(Vector2 myPos, Vector2 target) {
        return null;
    }

    @Override
    public int inflictDamage(Vector2 myPos, Vector2 attackPos) {
        return attackDmg;
    }

    @Override
    public int attacked(int damage) {
        return damage - armor;
    }
}

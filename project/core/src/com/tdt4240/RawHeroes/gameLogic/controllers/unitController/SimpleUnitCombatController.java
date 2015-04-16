package com.tdt4240.RawHeroes.gameLogic.controllers.unitController;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;

import java.util.ArrayList;

/**
 * Created by espen1 on 12.04.2015.
 */
public class SimpleUnitCombatController implements IUnitCombatController {

    private IUnit unit;
    private int attackDmg, armor;

    public SimpleUnitCombatController(IUnit u, int attackDmg, int armor){
        this.unit = u;
        this.attackDmg = attackDmg;
        this.armor = armor;
    }

    @Override
    public ArrayList<Vector2> getAttackablePositions(Vector2 pos, int movesLeft, IBoard board) {
        ArrayList<Vector2> temp = new ArrayList<Vector2>();
        if(pos.x < board.getWidth()-1){
            temp.add(new Vector2(pos.x+1, pos.y));
            System.out.println("Added to the right");
        }
        if(pos.x > 0){
            temp.add(new Vector2(pos.x-1, pos.y));
            System.out.println("Added to the left");
        }
        if(pos.y < board.getHeight()-1){
            temp.add(new Vector2(pos.x, pos.y+1));
            System.out.println("Added above");
        }
        if(pos.y > 0){
            temp.add(new Vector2(pos.x, pos.y-1));
            System.out.println("Added below");
        }
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

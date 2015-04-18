package com.tdt4240.RawHeroes.gameLogic.controllers.unitController;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by espen1 on 12.04.2015.
 */
public class SimpleUnitCombatController implements IUnitCombatController {

    private Random rand;
    private IUnit unit;
    private int attackDmgMin, attackDmgMax, armor;

    public SimpleUnitCombatController(IUnit u, int minDmg, int maxDmg, int armor){
        this.rand = new Random();
        this.unit = u;
        this.attackDmgMin = minDmg;
        this.attackDmgMax = maxDmg;
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
        return rand.nextInt((this.attackDmgMax - this.attackDmgMin) + 1 + attackDmgMin);
    }

    @Override
    public int attacked(int damage) {
        return damage - armor;
    }

    public int getAttackDmgMin(){
        return attackDmgMin;
    }

    public int getAttackDmgMax(){
        return attackDmgMax;
    }

    public int getArmor(){
        return armor;
    }
}

package com.tdt4240.RawHeroes.gameLogic.controllers.unitController;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.independent.Position;

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
    public ArrayList<Position> getAttackablePositions(Position pos, int movesLeft, IBoard board) {
        ArrayList<Position> temp = new ArrayList<Position>();
        System.out.println("energy: " + movesLeft);
        if(movesLeft <= 0) return temp;
        if(pos.getX() < board.getWidth()-1){
            temp.add(new Position(pos.getX()+1, pos.getY()));
            System.out.println("Added to the right");
        }
        if(pos.getX() > 0){
            temp.add(new Position(pos.getX()-1, pos.getY()));
            System.out.println("Added to the left");
        }
        if(pos.getY() < board.getHeight()-1){
            temp.add(new Position(pos.getX(), pos.getY()+1));
            System.out.println("Added above");
        }
        if(pos.getY() > 0){
            temp.add(new Position(pos.getX(), pos.getY()-1));
            System.out.println("Added below");
        }
        return temp;
    }

    @Override
    public ArrayList<Position> getInflictionZone(Position myPos, Position target) {
        ArrayList<Position> victimsPos = new ArrayList<Position>();
        victimsPos.add(target);
        return victimsPos;
    }

    @Override
    public int inflictDamage(Position myPos, Position attackPos) {
        return attackDmg;
    }

    @Override
    public int attacked(int damage) {
        return damage - armor;
    }
}

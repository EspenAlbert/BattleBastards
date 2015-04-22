package com.tdt4240.RawHeroes.gameLogic.controllers.unitController;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.independent.Position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by espen1 on 12.04.2015.
 */
public class SimpleUnitCombatController implements IUnitCombatController{
    public final static long serialVersionUID = 4567894235239423513l;

    private Random rand;
    private IUnit unit;
    private int attackDmgMin, attackDmgMax, armor;
    private int health;
    private final int maxHealth;

    public SimpleUnitCombatController(IUnit u, int minDmg, int maxDmg, int armor, int health) {
        this.rand = new Random();
        this.health = health;
        this.maxHealth = health;
        this.unit = u;
        this.attackDmgMin = minDmg;
        this.attackDmgMax = maxDmg;
        this.armor = armor;
    }

    @Override
    public ArrayList<Position> getAttackablePositions(Position pos, int movesLeft, IBoard board) {
        ArrayList<Position> temp = new ArrayList<Position>();
        System.out.println("energy: " + movesLeft);
        if (movesLeft <= 0) return temp;
        if (pos.getX() < board.getWidth() - 1) {
            temp.add(new Position(pos.getX() + 1, pos.getY()));
            System.out.println("Added to the right");
        }
        if (pos.getX() > 0) {
            temp.add(new Position(pos.getX() - 1, pos.getY()));
            System.out.println("Added to the left");
        }
        if (pos.getY() < board.getHeight() - 1) {
            temp.add(new Position(pos.getX(), pos.getY() + 1));
            System.out.println("Added above");
        }
        if (pos.getY() > 0) {
            temp.add(new Position(pos.getX(), pos.getY() - 1));
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
        return rand.nextInt(this.attackDmgMax - this.attackDmgMin) + this.attackDmgMin + 1;
    }

    @Override
    public int attacked(int damage) {
        int dmgInflicted = damage - armor;
        this.health -= dmgInflicted;
        return dmgInflicted;
    }

    @Override
    public int getMinAttackDmg() {
        return attackDmgMin;
    }

    @Override
    public int getMaxAttackDmg() {
        return attackDmgMax;
    }

    public int getArmor() {
        return armor;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

}


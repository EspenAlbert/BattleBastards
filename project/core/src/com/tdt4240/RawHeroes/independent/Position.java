package com.tdt4240.RawHeroes.independent;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.NumberUtils;

import java.io.Serializable;

/**
 * Created by Endre on 16.04.2015.
 */
public class Position implements Serializable, Comparable<Position>{
    private final static long serialVersionUID = 7155890697978244635l;
    private int x;
    private int y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }public Position(Position position){
        this(position.getX(), position.getY());
    }

    public Position getPos(){
        return this;
    }
    public Vector2 getVec2Pos(){
        return new Vector2(this.x, this.y);
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public Position add(int x, int y){
        this.x += x;
        this.y += y;
        return this;
    }
    public Position add(Position position){
        this.x += position.getX();
        this.y += position.getY();
        return this;
    }
    public Position sub(int x, int y){
        this.x -= x;
        this.y -= y;
        return this;
    }
    public Position sub(Position position){
        this.x -= position.getX();
        this.y -= position.getY();
        return this;
    }
    public Position cpy(){
        return new Position(this.x, this.y);
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Position){
            if (obj == this)return true;
            if(((Position) obj).getX() == this.x && ((Position) obj).getY() == this.y)return true;
        }
        return false;
    }
    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + NumberUtils.floatToIntBits(x);
        result = prime * result + NumberUtils.floatToIntBits(y);
        return result;
    }

    @Override
    public int compareTo(Position p) {
        if(this.getY() > p.getY()) return -1;
        if(this.getY() < p.getY()) return 1;
        return 0;
    }
    @Override
    public String toString() {
        return x + "," + y;
    }
}

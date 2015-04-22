package com.tdt4240.RawHeroes.independent;

import java.io.Serializable;

/**
 * Created by espen1 on 15.04.2015.
 */
public class Pair<L,R> implements Serializable{

    private final L key;
    private final R value;

    public Pair(L key, R right) {
        this.key = key;
        this.value = right;
    }

    public L getKey() { return key; }
    public R getValue() { return value; }

    @Override
    public int hashCode() { return key.hashCode() ^ value.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) return false;
        Pair pairo = (Pair) o;
        return this.key.equals(pairo.getKey()) &&
                this.value.equals(pairo.getValue());
    }


}
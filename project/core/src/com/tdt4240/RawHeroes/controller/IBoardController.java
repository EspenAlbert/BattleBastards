package com.tdt4240.RawHeroes.controller;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface IBoardController {
    void cellTouched(Vector2 coordinates);
    void attackButtonTouched();

    void selectPiece(Vector2 coordinate);
    void deSelectPiece();

}

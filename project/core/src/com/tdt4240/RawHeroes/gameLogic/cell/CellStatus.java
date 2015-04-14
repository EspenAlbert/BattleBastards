package com.tdt4240.RawHeroes.gameLogic.cell;

/**
 * Created by espen1 on 27.02.2015.
 */
public enum CellStatus {
    SELECTABLE, ATTACKABLE, MOVEABLE, DEFAULT, SELECTED, NOTMOVEABLE;
    //NOTMOVEABLE is a cell that a unit never can move to(like rocks or forests)
}

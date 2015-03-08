package com.tdt4240.RawHeroes.gameLogic.cell;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;

/**
 * Created by espen1 on 07.03.2015.
 */
public class Cell implements ICell {
    private final Vector2 pos;
    private IUnit unit;
    private CellStatus cellStatus;

    public Cell(int x, int y, CellStatus cellStatus) {
        this.cellStatus = cellStatus;
        this.pos = new Vector2(x, y);
    }

    @Override
    public Vector2 getPos() {
        return null;
    }

    @Override
    public IUnit getUnit() {
        return unit;
    }

    @Override
    public CellStatus getStatus() {
        return cellStatus;
    }
    @Override
    public void setUnit(IUnit unit) {
        this.unit = unit;
    }

    @Override
    public void setStatus(CellStatus status) {
        cellStatus = status;
    }
}

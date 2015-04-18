package com.tdt4240.RawHeroes.gameLogic.cell;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.independent.Position;

/**
 * Created by espen1 on 07.03.2015.
 */
public class Cell implements ICell {
    private Position pos;
    private IUnit unit;
    private CellStatus cellStatus;

    public Cell(int x, int y, CellStatus cellStatus) {
        this.cellStatus = cellStatus;
        this.pos = new Position(x, y);
    }

    @Override
    public Position getPos() {
        return pos;
    }
    @Override
    public void setPos(Position value) {
        pos = value;
    }

    @Override
    public IUnit getUnit() {
        return unit != null ? unit:null;
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

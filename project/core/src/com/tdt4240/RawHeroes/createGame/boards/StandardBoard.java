package com.tdt4240.RawHeroes.createGame.boards;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.createUnits.factory.UnitBuilding;
import com.tdt4240.RawHeroes.event.events.BoardEvent;
import com.tdt4240.RawHeroes.event.events.CellChangeEvent;
import com.tdt4240.RawHeroes.event.listener.IBoardListener;
import com.tdt4240.RawHeroes.gameLogic.cell.Cell;
import com.tdt4240.RawHeroes.gameLogic.cell.CellStatus;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;

import java.util.ArrayList;

/**
 * Created by espen1 on 07.03.2015.
 */
public class StandardBoard implements IBoard {

    private final int width = 7;
    private final int height = 7;

    private ICell[][] cells;
    private ArrayList<IBoardListener> listeners;

    public StandardBoard() {
        cells = new ICell[width][height];
        for(int x = 0; x< width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y, CellStatus.DEFAULT);
            }
        }
        //Player 1 unit
        cells[0][0].setUnit(UnitBuilding.getInstance().createUnit(UnitName.STANDARD_UNIT, true));
        cells[0][1].setUnit(UnitBuilding.getInstance().createUnit(UnitName.STANDARD_UNIT, true));
        //Player 2 unit
        cells[width-1][height-1].setUnit(UnitBuilding.getInstance().createUnit(UnitName.STANDARD_UNIT, false));
        listeners = new ArrayList<IBoardListener>();
    }

    public StandardBoard(ICell[][] cells) {
        this.cells = cells.clone();
    }

    @Override
    public void addBoardListener(IBoardListener listener) {
        listeners.add(listener);
    }

    @Override
    public void fireBoardChanged(BoardEvent event) {
        for(IBoardListener listener : listeners) {
            listener.boardChanged(event);
        }
    }

    @Override
    public ICell getCell(Vector2 pos) {
        return cells[((int) pos.x)][((int) pos.y)];
    }

    @Override
    public ICell[][] getCells() {
        return cells;
    }

    @Override
    public void switchModeOnCell(Vector2 pos, CellStatus status) {
        cells[((int) pos.x)][((int) pos.y)].setStatus(status);
        fireBoardChanged(new CellChangeEvent(pos));

    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public IBoard deepCopy() {
        return new StandardBoard(cells);
    }


}

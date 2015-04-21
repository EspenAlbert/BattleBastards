package com.tdt4240.RawHeroes.createGame.boards;

import com.tdt4240.RawHeroes.createUnits.factory.UnitBuilding;
import com.tdt4240.RawHeroes.event.events.BoardEvent;
import com.tdt4240.RawHeroes.event.events.BoardResetEvent;
import com.tdt4240.RawHeroes.event.events.CellChangeEvent;
import com.tdt4240.RawHeroes.event.listener.IBoardListener;
import com.tdt4240.RawHeroes.gameLogic.cell.Cell;
import com.tdt4240.RawHeroes.gameLogic.cell.CellStatus;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CellConverter;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.gameLogic.unit.UnitName;
import com.tdt4240.RawHeroes.independent.Position;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.IRenderObject;

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
        cells = createStandardBoardCells(false);
        //Player 1 unit
        cells[1][0].setUnit(UnitBuilding.getInstance().createUnit(UnitName.STANDARD_UNIT, true));
        cells[2][0].setUnit(UnitBuilding.getInstance().createUnit(UnitName.STANDARD_UNIT_2, true));
        cells[3][0].setUnit(UnitBuilding.getInstance().createUnit(UnitName.STANDARD_UNIT_2, true));
        cells[4][0].setUnit(UnitBuilding.getInstance().createUnit(UnitName.STANDARD_UNIT, true));
        //Player 2 unit
        cells[1][6].setUnit(UnitBuilding.getInstance().createUnit(UnitName.STANDARD_UNIT, false));
        cells[2][6].setUnit(UnitBuilding.getInstance().createUnit(UnitName.STANDARD_UNIT_2, false));
        cells[3][6].setUnit(UnitBuilding.getInstance().createUnit(UnitName.STANDARD_UNIT_2, false));
        cells[4][6].setUnit(UnitBuilding.getInstance().createUnit(UnitName.STANDARD_UNIT, false));

        listeners = new ArrayList<IBoardListener>();
    }

    private ICell[][] createStandardBoardCells(boolean fromOriginal) {
        ICell[][] newCells = new ICell[width][height];
        for(int x = 0; x< width; x++) {
            for (int y = 0; y < height; y++) {
                newCells[x][y] = new Cell(x, y, fromOriginal ? cells[x][y].getStatus() : CellStatus.DEFAULT);
            }
        }
        return newCells;
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
    public ICell getCell(Position pos) {
        return cells[((int) pos.getX())][((int) pos.getY())];
    }

    @Override
    public ICell[][] getCells() {
        return cells;
    }

    @Override
    public void switchModeOnCell(Position pos, CellStatus status) {
        cells[((int) pos.getX())][((int) pos.getY())].setStatus(status);
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
    public ICell[][] deepCopy() {
        ArrayList<Position> unitPositions = getUnitPositions();
        ICell[][] newCells = createStandardBoardCells(true);
        for(Position pos: unitPositions) {
            newCells[pos.getX()][pos.getY()].setUnit(cells[pos.getX()][pos.getY()].getUnit().getCopy());
        }
        return newCells;
    }

    @Override
    public void convertCellsToOtherPlayer() {
        this.cells = CellConverter.convertCells(cells);
    }

    @Override
    public void changeCells(ICell[][] initialBoard) {
        cells = initialBoard;
        fireBoardChanged(new BoardResetEvent());
    }

    @Override
    public ArrayList<Position> getUnitPositions() {
        ArrayList<Position> unitPositions = new ArrayList<Position>();
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[0].length; y++) {
                if (cells[x][y].getUnit() != null) {
                    unitPositions.add(new Position(x, y));
                }
            }
        }
        return unitPositions;
    }



}

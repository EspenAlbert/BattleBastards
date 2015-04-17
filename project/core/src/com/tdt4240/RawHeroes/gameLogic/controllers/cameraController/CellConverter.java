package com.tdt4240.RawHeroes.gameLogic.controllers.cameraController;

import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.independent.Position;

/**
 * Created by espen1 on 27.02.2015.
 */
public class CellConverter{
    public static ICell[][] convertCells(ICell[][] cells) {
        int xLength = cells.length;
        int yLength = cells[0].length;
        ICell[][] returnCells = new ICell[xLength][yLength];
        for(int x = 0; x < xLength; x++) {
            for(int y = 0; y < yLength; y++) {
                int xCoordinate = xLength - (1+x);
                int yCoordinate = yLength - (1+y);
                returnCells[x][y] = cells[xCoordinate][yCoordinate];
                returnCells[x][y].setPos(new Position(x, y));
            }
        }
        return returnCells;
    }
}

package com.tdt4240.RawHeroes.gameLogic.controllers.cameraController;

import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.independent.Position;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by espen1 on 27.02.2015.
 */
public class CellConverter{

    private HashMap<Position, Position> localConvertedPositions;
    private int xLength;
    private int yLength;

    //For the server so that we avoid concurrency problems
    public CellConverter(int xLength, int yLength) {
        this.xLength = xLength;
        this.yLength = yLength;
        localConvertedPositions = new HashMap<Position, Position>();
    }

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

    //Used by the server
    public Position switchPosition(Position pos) {
        if(localConvertedPositions.containsKey(pos)) {
            //IF distance from previous request > 1 return other...
            return localConvertedPositions.get(pos);
        }
        int x = pos.getX();
        int y = pos.getY();
        int xCoordinate = xLength - (1+x);
        int yCoordinate = yLength - (1+y);
        Position newPos = new Position(xCoordinate, yCoordinate);
        localConvertedPositions.put(pos, newPos);
        System.out.println("Added entry: " + pos + "|" + newPos);
        return newPos;
    }

}

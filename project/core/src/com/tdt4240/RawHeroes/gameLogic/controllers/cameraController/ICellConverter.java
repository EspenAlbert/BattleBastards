package com.tdt4240.RawHeroes.gameLogic.controllers.cameraController;

import com.tdt4240.RawHeroes.gameLogic.cell.ICell;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface ICellConverter {
    ICell[][] convertCells(ICell[][] cells);

}

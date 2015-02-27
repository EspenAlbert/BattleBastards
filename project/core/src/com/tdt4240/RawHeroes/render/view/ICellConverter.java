package com.tdt4240.RawHeroes.render.view;

import com.tdt4240.RawHeroes.logic.cell.ICell;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface ICellConverter {
    ICell[][] convertCells(ICell[][] cells);

}

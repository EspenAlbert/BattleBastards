package com.tdt4240.RawHeroes.event.move;

import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CellConverter;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

/**
 * Created by Håvard on 21.04.2015.
 */
public class SkipMove extends Move{
    public final static long serialVersionUID = 4869273638495955552l;

    public SkipMove(ICell start, ICell end) {
        super(start, end);
    }

    @Override
    public void execute(IBoard board) {

    }

    @Override
    public void undo(IBoard board) {

    }

    @Override
    public void convertPositions(CellConverter converter) {

    }
}

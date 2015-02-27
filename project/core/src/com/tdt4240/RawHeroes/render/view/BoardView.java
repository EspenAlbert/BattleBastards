package com.tdt4240.RawHeroes.render.view;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.tdt4240.RawHeroes.logic.cell.ICell;
import com.tdt4240.RawHeroes.logic.events.AttackEvent;
import com.tdt4240.RawHeroes.logic.events.BoardEvent;
import com.tdt4240.RawHeroes.logic.events.CellChange;
import com.tdt4240.RawHeroes.logic.events.MovementMove;
import com.tdt4240.RawHeroes.logic.model.IBoard;
import com.tdt4240.RawHeroes.logic.modelListener.IBoardListener;
import com.tdt4240.RawHeroes.render.lowLayer.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by espen1 on 27.02.2015.
 */
public class BoardView implements IBoardListener{



    private final int boardWidth;
    private final int boardHeight;
    private final IBoard board;
    private final ICellConverter cellConverter;
    private final int extraButtonsInHeight;
    private final int extraButtonsInWidth;

    private ArrayList<ArrayList<Button>> buttons;
    private int cameraY;
    private int cameraX;

    public BoardView(IBoard board, ICellConverter cellConverter) {
        this.board = board;
        board.addBoardListener(this);
        this.cellConverter = cellConverter;
        ICell[][] cells = cellConverter.convertCells(board.getCells());

        boardWidth = cells.length;
        boardHeight = cells[0].length;

        int buttonWidth = Constants.RESOLUTION_WIDTH / 8;
        int buttonHeight = Constants.RESOLUTION_HEIGHT / 16;
        extraButtonsInHeight = boardHeight - 16 > 0 ? boardHeight - 16 : 0;
        extraButtonsInWidth = boardWidth - 8 > 0 ? boardWidth - 8 : 0;
        cameraX = 0;
        cameraY = 0;
        Vector2 buttonPos = new Vector2(0,0);
        buttons = null;
        for (int x = 0; x < boardWidth; x++) {
            ArrayList<Button> currentColumn = new ArrayList<Button>();
            for (int y = 0; y < boardHeight; y++) {
               //Skin currentColumn.add(new Button(//Skin));
            }
            buttons.add(currentColumn);
        }



    }

    @Override
    public void BoardChanged(BoardEvent event) {
        if(event instanceof CellChange) {
            // change button for cell
        } else if(event instanceof AttackEvent) {

        }
        else if(event instanceof MovementMove) {

        }
    }
}

package com.tdt4240.RawHeroes.view.customUIElements.boardRenderer;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.event.events.BoardEvent;
import com.tdt4240.RawHeroes.event.listener.IBoardListener;
import com.tdt4240.RawHeroes.gameLogic.cell.CellStatus;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.ICellConverter;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.Player1CellConverter;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.Player2CellConverter;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.independent.GameConstants;

import java.util.ArrayList;

/**
 * Created by espen1 on 12.03.2015.
 */
public class BoardRenderer implements IBoardListener {

    private final int boardWidth;
    private final int boardHeight;
    private final IBoard board;
    private final ICellConverter cellConverter;
    private final int extraButtonsInHeight;
    private final int extraButtonsInWidth;
    private CellStatus[][] cellStatuses;

    private int cameraY;
    private int cameraX;

    public BoardRenderer(IBoard board, boolean iAmPlayer1) {
        this.board = board;
        board.addBoardListener(this);
        this.cellConverter = iAmPlayer1 ? new Player1CellConverter() : new Player2CellConverter();

        ICell[][] cells = cellConverter.convertCells(board.getCells());

        boardWidth = cells.length;
        boardHeight = cells[0].length;

        int buttonWidth = GameConstants.RESOLUTION_WIDTH / 8;
        int buttonHeight = GameConstants.RESOLUTION_HEIGHT / 16;
        extraButtonsInHeight = boardHeight - 16 > 0 ? boardHeight - 16 : 0;
        extraButtonsInWidth = boardWidth - 8 > 0 ? boardWidth - 8 : 0;
        cameraX = 0;
        cameraY = 0;
        Vector2 buttonPos = new Vector2(0, 0);
        ArrayList<Vector2> unitPositions = new ArrayList<Vector2>();
        cellStatuses = new CellStatus[boardWidth][boardHeight];
        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++) {
                cellStatuses[x][y] = cells[x][y].getStatus();
            }
        }
    }

    @Override
    public void boardChanged(BoardEvent event) {

    }
}

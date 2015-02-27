package com.tdt4240.RawHeroes.render.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.tdt4240.RawHeroes.logic.cell.ICell;
import com.tdt4240.RawHeroes.logic.events.AttackEvent;
import com.tdt4240.RawHeroes.logic.events.BoardEvent;
import com.tdt4240.RawHeroes.logic.events.CellChange;
import com.tdt4240.RawHeroes.logic.events.MovementMove;
import com.tdt4240.RawHeroes.logic.model.IBoard;
import com.tdt4240.RawHeroes.logic.modelListener.IBoardListener;
import com.tdt4240.RawHeroes.logic.move.AttackMove;
import com.tdt4240.RawHeroes.logic.move.IMoveListener;
import com.tdt4240.RawHeroes.logic.move.Move;
import com.tdt4240.RawHeroes.render.lowLayer.Constants;
import com.tdt4240.RawHeroes.render.topLayer.IRenderObject;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public class BoardView implements IBoardListener, IBoardView, IRenderObject {

    private final UnitRenderer unitRenderer;

    private final int boardWidth;
    private final int boardHeight;
    private final IBoard board;
    private final ICellConverter cellConverter;
    private final int extraButtonsInHeight;
    private final int extraButtonsInWidth;

    private ArrayList<ArrayList<Button>> buttons;

    private int cameraY;
    private int cameraX;

    public BoardView(IBoard board, boolean iAmPlayer1) {
        this.board = board;
        board.addBoardListener(this);
        this.cellConverter = iAmPlayer1 ? new Player1CellConverter() : new Player2CellConverter();

        ICell[][] cells = cellConverter.convertCells(board.getCells());

        boardWidth = cells.length;
        boardHeight = cells[0].length;

        int buttonWidth = Constants.RESOLUTION_WIDTH / 8;
        int buttonHeight = Constants.RESOLUTION_HEIGHT / 16;
        extraButtonsInHeight = boardHeight - 16 > 0 ? boardHeight - 16 : 0;
        extraButtonsInWidth = boardWidth - 8 > 0 ? boardWidth - 8 : 0;
        cameraX = 0;
        cameraY = 0;
        Vector2 buttonPos = new Vector2(0, 0);
        buttons = new ArrayList<ArrayList<Button>>();
        ArrayList<Vector2> unitPositions = new ArrayList<>();
        for (int x = 0; x < boardWidth; x++) {
            ArrayList<Button> currentColumn = new ArrayList<Button>();
            for (int y = 0; y < boardHeight; y++) {
                //Skin currentColumn.add(new Button(//Skin));
                if (cells[x][y].getUnit() != null) {
                    unitPositions.add(new Vector2(x, y));
                }
            }
            buttons.add(currentColumn);
        }
        unitRenderer = new UnitRenderer(unitPositions, board);


    }

    @Override
    public void BoardChanged(BoardEvent event) {
        if (event instanceof CellChange) {
            // change button for cell
        } else if (event instanceof AttackEvent) {

        } else if (event instanceof MovementMove) {

        }
    }

    @Override
    public Vector2 convertPixelCoordinateToCell(Vector2 pixelCoordinate) {
        return null;
    }



    @Override
    public void render(SpriteBatch batch) {
        unitRenderer.render(batch);
    }
}

package com.tdt4240.RawHeroes.view.topLayer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.tdt4240.RawHeroes.event.events.AttackEvent;
import com.tdt4240.RawHeroes.event.listener.IBoardListener;
import com.tdt4240.RawHeroes.event.events.CellChangeEvent;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.ICamera;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.event.events.BoardEvent;
import com.tdt4240.RawHeroes.event.events.MovementMove;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.ICellConverter;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.Player1CellConverter;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.Player2CellConverter;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.UnitRenderer;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public class GameView implements IView, IBoardListener, ICamera {

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

    public GameView(IBoard board, boolean iAmPlayer1) {
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
        buttons = new ArrayList<ArrayList<Button>>();
        ArrayList<Vector2> unitPositions = new ArrayList<Vector2>();
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
        unitRenderer = new UnitRenderer(unitPositions, board, this);


    }

    public void BoardChanged(BoardEvent event) {
        if (event instanceof CellChangeEvent) {
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

    @Override
    public void boardChanged(BoardEvent event) {

    }
}

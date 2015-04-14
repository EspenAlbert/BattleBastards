package com.tdt4240.RawHeroes.view.customUIElements.boardRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.IRender;

import java.util.ArrayList;

/**
 * Created by espen1 on 12.03.2015.
 */
public class BoardRenderer implements IBoardListener, IRender {

    private final int boardWidth;
    private final int boardHeight;
    private final IBoard board;
    private final ICellConverter cellConverter;
    private final int extraButtonsInHeight;
    private final int extraButtonsInWidth;
    private final int buttonWidth;
    private final int buttonHeight;
    private final int spaceBetween;
    private CellStatus[][] cellStatuses;
    public static Texture ordinaryCell = new Texture(Gdx.files.internal("badlogic.jpg"));

    private int cameraY;
    private int cameraX;

    public BoardRenderer(IBoard board, boolean iAmPlayer1) {
        this.board = board;
        board.addBoardListener(this);
        this.cellConverter = iAmPlayer1 ? new Player1CellConverter() : new Player2CellConverter();

        ICell[][] cells = cellConverter.convertCells(board.getCells());



        boardWidth = cells.length;
        boardHeight = cells[0].length;

        buttonWidth = GameConstants.BUTTON_WIDTH;
        buttonHeight = GameConstants.BUTTON_HEIGHT;
        spaceBetween = GameConstants.SPACE_BETWEEN;

        extraButtonsInHeight = boardHeight - 8 > 0 ? boardHeight - 8 : 0;
        extraButtonsInWidth = boardWidth - 4 > 0 ? boardWidth - 4 : 0;
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

    @Override
    public void render(SpriteBatch batch, Vector2 pos) {

        int yPos = 0;
        int xPos = 0;
        for(int x=0; x < 4; x++) {
            yPos =0;
            for(int y= 0; y < 8; y++) {
              //  batch.draw(ordinaryCell, xPos, yPos, 10, 10);
               // batch.draw(ordinaryCell, buttonWidth * x, buttonHeight * y, buttonWidth, buttonHeight);

                yPos += buttonHeight+spaceBetween;
            }
            xPos += buttonWidth+ spaceBetween;
        }

        batch.draw(ordinaryCell, 0, 0, buttonWidth, buttonHeight, 0, 0, ordinaryCell.getWidth(), ordinaryCell.getHeight(), false, false);
        //batch.draw(ordinaryCell, 50, 50, ordinaryCell.getWidth() / 2, ordinaryCell.getHeight() / 2, ordinaryCell.getWidth(), ordinaryCell.getHeight(), 1,1, 0, 0, 0, ordinaryCell.getWidth(), ordinaryCell.getHeight(), false, false);
        //sb.draw(texture,x, y, texture.getWidth() / 2, texture.getHeight() / 2, texture.getWidth(), texture.getHeight(), 1, 1, body.getAngle() * MathUtils.radiansToDegrees, 0, 0, texture.getWidth(), texture.getHeight(), false, flipY);

    }
}

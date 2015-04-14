package com.tdt4240.RawHeroes.view.customUIElements.boardRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
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
    private CellStatus[][] cellStatuses;
    public static Texture ordinaryCell = new Texture(Gdx.files.internal("tiles/Grass.png"));
    public static Texture gridCell = new Texture(Gdx.files.internal("gridOverlay/gridBlack.png"));

    private int cameraY;
    private int cameraX;

    public BoardRenderer(IBoard board, boolean iAmPlayer1) {
        this.board = board;
        board.addBoardListener(this);
        this.cellConverter = iAmPlayer1 ? new Player1CellConverter() : new Player2CellConverter();

        ICell[][] cells = cellConverter.convertCells(board.getCells());



        boardWidth = cells.length;
        boardHeight = cells[0].length;

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
        Gdx.gl.glClearColor(0.5f, 0.75f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        for (int x = 0; x < this.boardWidth; x++){
            for (int y = 0; y < this.boardHeight; y++){
                batch.draw(ordinaryCell,                    //Texture
                           x*GameConstants.BUTTON_WIDTH,    //xpos for texture
                           y*GameConstants.BUTTON_HEIGHT,   //ypos for texture
                           GameConstants.BUTTON_WIDTH,      //Bredden til texture
                           GameConstants.BUTTON_HEIGHT);    //Høyden til texture

                batch.draw(gridCell,                        //Texture
                           x*GameConstants.BUTTON_WIDTH,    //xpos for texture
                           y*GameConstants.BUTTON_HEIGHT,   //ypos for texture
                           GameConstants.BUTTON_WIDTH,      //Bredden til texture
                           GameConstants.BUTTON_HEIGHT);    //Høyden til texture
            }
        }
        batch.end();
        //batch.draw(ordinaryCell, 50, 50, ordinaryCell.getWidth() / 2, ordinaryCell.getHeight() / 2, ordinaryCell.getWidth(), ordinaryCell.getHeight(), 1,1, 0, 0, 0, ordinaryCell.getWidth(), ordinaryCell.getHeight(), false, false);
        //sb.draw(texture,x, y, texture.getWidth() / 2, texture.getHeight() / 2, texture.getWidth(), texture.getHeight(), 1, 1, body.getAngle() * MathUtils.radiansToDegrees, 0, 0, texture.getWidth(), texture.getHeight(), false, flipY);

    }
}

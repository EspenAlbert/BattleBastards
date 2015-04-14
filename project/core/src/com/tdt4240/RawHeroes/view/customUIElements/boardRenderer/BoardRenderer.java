package com.tdt4240.RawHeroes.view.customUIElements.boardRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.event.events.BoardEvent;
import com.tdt4240.RawHeroes.event.events.CellChangeEvent;
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
    private ArrayList<ArrayList<Sprite>> sprites;
    private CellStatus[][] cellStatuses;
    public static Texture ordinaryCell = new Texture(Gdx.files.internal("cell.png"));
    public static Texture attackableCell = new Texture(Gdx.files.internal("badlogic.jpg"));

    private int cameraY;
    private int cameraX;

    public BoardRenderer(IBoard board, boolean iAmPlayer1) {
        this.board = board;
        board.addBoardListener(this);
        this.cellConverter = iAmPlayer1 ? new Player1CellConverter() : new Player2CellConverter();

        ICell[][] cells = cellConverter.convertCells(board.getCells());



        boardWidth = cells.length;
        boardHeight = cells[0].length;


        cellStatuses = new CellStatus[boardWidth][boardHeight];

        sprites = new ArrayList<ArrayList<Sprite>>();

        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++) {
                cellStatuses[x][y] = cells[x][y].getStatus();
            }
        }
        for(int x = 0; x< boardWidth; x++) {
            ArrayList<Sprite> columnSprites = new ArrayList<Sprite>();
            for(int y =0; y < boardHeight; y++) {
                Sprite sprite = new Sprite(ordinaryCell);
                sprite.setSize(0.95f, 0.95f);
                sprite.setPosition(x, y);
                columnSprites.add(sprite);
            }
            sprites.add(columnSprites);
        }

    }

    @Override
    public void boardChanged(BoardEvent event) {
        if(event instanceof CellChangeEvent) {
            CellChangeEvent cellChangeEvent = (CellChangeEvent) event;
            System.out.println(cellChangeEvent.getPosision().x + "," + cellChangeEvent.getPosision().y + " cell was changed to:" + board.getCell(cellChangeEvent.getPosision()).getStatus());
            sprites.get((int) cellChangeEvent.getPosision().x).get((int) cellChangeEvent.getPosision().y).setTexture(attackableCell);
        }
    }

    @Override
    public void render(SpriteBatch batch, Vector2 pos) {
        for(ArrayList<Sprite> columnSprites: sprites) {
            for(Sprite sprite : columnSprites) {
                sprite.draw(batch);
            }
        }
        //batch.draw(ordinaryCell, 0, 0, buttonWidth, buttonHeight, 0, 0, ordinaryCell.getWidth(), ordinaryCell.getHeight(), false, false);
        //batch.draw(ordinaryCell, 50, 50, ordinaryCell.getWidth() / 2, ordinaryCell.getHeight() / 2, ordinaryCell.getWidth(), ordinaryCell.getHeight(), 1,1, 0, 0, 0, ordinaryCell.getWidth(), ordinaryCell.getHeight(), false, false);
        //sb.draw(texture,x, y, texture.getWidth() / 2, texture.getHeight() / 2, texture.getWidth(), texture.getHeight(), 1, 1, body.getAngle() * MathUtils.radiansToDegrees, 0, 0, texture.getWidth(), texture.getHeight(), false, flipY);

    }
}

package com.tdt4240.RawHeroes.view.customUIElements.boardRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tdt4240.RawHeroes.event.events.BoardEvent;
import com.tdt4240.RawHeroes.event.events.CellChangeEvent;
import com.tdt4240.RawHeroes.event.listener.IBoardListener;
import com.tdt4240.RawHeroes.gameLogic.cell.CellStatus;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.independent.Position;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.IRenderNoPos;

import java.util.ArrayList;

/**
 * Created by espen1 on 12.03.2015.
 */
public class BoardRenderer implements IBoardListener, IRenderNoPos {

    private final int boardWidth;
    private final int boardHeight;
    private final IBoard board;
    private ArrayList<ArrayList<Sprite>> overlaySprites;
    private ArrayList<ArrayList<Sprite>> grassSprites;
    //public static Texture attackableCell = new Texture(Gdx.files.internal("badlogic.jpg"));
    private CellStatus[][] cellStatuses;
    public static Texture ordinaryCell = new Texture(Gdx.files.internal("tiles/Grass.png"));
    public static Texture defaultCell = new Texture(Gdx.files.internal("gridOverlay/gridBlack.png"));
    public static Texture attackableCell = new Texture(Gdx.files.internal("gridOverlay/gridRed.png"));
    public static Texture inMovementRangeCell = new Texture(Gdx.files.internal("gridOverlay/gridGreen.png"));
    public static Texture selectedCell = new Texture(Gdx.files.internal("gridOverlay/gridWhite.png"));

    public BoardRenderer(IBoard board, boolean iAmPlayer1) {
        this.board = board;
        board.addBoardListener(this);
        ICell[][] cells = board.getCells();
        boardWidth = cells.length;
        boardHeight = cells[0].length;
        cellStatuses = new CellStatus[boardWidth][boardHeight];
        grassSprites = new ArrayList<ArrayList<Sprite>>();
        overlaySprites = new ArrayList<ArrayList<Sprite>>();
        setupSprites(cells);
    }

    private void setupSprites(ICell[][] cells) {
        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++) {
                cellStatuses[x][y] = cells[x][y].getStatus();
            }
        }
        for(int x = 0; x< boardWidth; x++) {
            ArrayList<Sprite> columnSprites = new ArrayList<Sprite>();
            ArrayList<Sprite> columnSpritesOverlay = new ArrayList<Sprite>();
            for(int y =0; y < boardHeight; y++) {
                Sprite sprite = new Sprite(ordinaryCell);
                sprite.setSize(1f, 1f);
                sprite.setPosition(x, y);
                columnSprites.add(sprite);
                columnSpritesOverlay.add(createOverlaySprite(x, y));
            }
            grassSprites.add(columnSprites);
            overlaySprites.add(columnSpritesOverlay);
        }
    }

    private Sprite createOverlaySprite(int x, int y) {
        CellStatus status = cellStatuses[x][y];
        Sprite spriteOverlay = null;
        switch (status) {
            case ATTACKABLE:
                spriteOverlay = new Sprite(attackableCell);
                break;
            case IN_MOVING_RANGE:
                spriteOverlay = new Sprite(inMovementRangeCell);
                break;
            case DEFAULT:
                spriteOverlay = new Sprite(defaultCell);
                break;
            case SELECTED:
                spriteOverlay = new Sprite(selectedCell);
                break;
            case NOTMOVEABLE:
                spriteOverlay = new Sprite(defaultCell);
                break;
        }
        spriteOverlay.setSize(1f, 1f);
        spriteOverlay.setPosition(x, y);
        return spriteOverlay;
    }

    @Override
    public void boardChanged(BoardEvent event) {
        if(event instanceof CellChangeEvent) {
            CellChangeEvent cellChangeEvent = (CellChangeEvent) event;
            Position pos = cellChangeEvent.getPosition();
            int x = (int) pos.getX();
            int y = (int) pos.getY();
            cellStatuses[x][y] = board.getCell(pos).getStatus();
            overlaySprites.get(x).set(y, createOverlaySprite(x, y));
        } else {//Event is reset event
            setupSprites(board.getCells());
        }
    }

    @Override
    public void render(SpriteBatch batch) {

        for(ArrayList<Sprite> columnSprites: grassSprites) {
            for(Sprite sprite : columnSprites) {
                sprite.draw(batch);
            }
        }
        for(ArrayList<Sprite> columnSprites: overlaySprites) {
            for(Sprite sprite : columnSprites) {
                sprite.draw(batch);
            }
        }
    }
}

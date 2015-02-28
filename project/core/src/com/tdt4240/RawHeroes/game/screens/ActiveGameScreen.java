package com.tdt4240.RawHeroes.game.screens;

import com.tdt4240.RawHeroes.controller.BoardController.BoardController;
import com.tdt4240.RawHeroes.controller.BoardMover;
import com.tdt4240.RawHeroes.controller.BoardController.IBoardController;
import com.tdt4240.RawHeroes.controller.IBoardMover;
import com.tdt4240.RawHeroes.game.gameState.GameState;
import com.tdt4240.RawHeroes.logic.model.IBoard;
import com.tdt4240.RawHeroes.network.client.ClientConnection;
import com.tdt4240.RawHeroes.render.view.BoardView;

/**
 * Created by espen1 on 27.02.2015.
 */
public class ActiveGameScreen extends ScreenState {
    private final BoardView boardView;
    private final IBoardMover boardMover;
    private final IBoardController boardController;
    private final IBoard board;
    private final boolean iAmPlayer1;

    public ActiveGameScreen(ScreenStateManager gsm, GameState gameState) {
        super(gsm);
        board = gameState.getBoard();
        iAmPlayer1 = ClientConnection.getInstance().getUsername().equals(gameState.getPlayer1Nickname());
        boardView = new BoardView(board, iAmPlayer1);
        board.addBoardListener(boardView);

        boardMover = new BoardMover(board);
        boardMover.executeMoves(gameState.getLastMoves());
        boardController = new BoardController(board, boardMover, gameState.getMoveCount());
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {

    }

    @Override
    public void dispose() {

    }
}

package com.tdt4240.RawHeroes.topLayer.screens;

import com.tdt4240.RawHeroes.createGame.factory.GameBuilding;
import com.tdt4240.RawHeroes.event.move.AttackMove;
import com.tdt4240.RawHeroes.event.move.Move;
import com.tdt4240.RawHeroes.event.move.MovementMove;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.BoardMover;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.SimpleUnitCombatController;
import com.tdt4240.RawHeroes.gameLogic.controllers.unitController.WalkingUnitMovementController;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.independent.Position;
import com.tdt4240.RawHeroes.network.client.ClientConnection;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Games;

import java.util.ArrayList;

/**
 * Created by espen1 on 19.04.2015.
 */
public class EspenSuperTesterScreen extends ScreenState {


    private Game game;
    private IBoard board;
    private BoardMover boardMover;
    private SimpleUnitCombatController simpleUnitCombatController;
    private IUnit attackUnit;
    private WalkingUnitMovementController movementController;
    private int counter = 0;
    private int timeSinceLast = 1000;
    private ArrayList<Move> prevTurn;


    protected EspenSuperTesterScreen(ScreenStateManager gsm) {
        super(gsm);
        game = GameBuilding.getInstance().createGame(Games.KILL_ALL_ENEMY_UNITS, "p1", "p2");

        board = game.getBoard();
        movementController = new WalkingUnitMovementController();
        simpleUnitCombatController = new SimpleUnitCombatController(attackUnit, 5, 9, 1);
        boardMover = new BoardMover(board);




    }

    private void round3() {
        System.out.println("Unit life should be less than 20, is:" + board.getCell(new Position(1,2)).getUnit().getHealth());
    }
    private void round2() {
        /////////////////////2. round /////////////////////
        ClientConnection.getInstance().setUsername("p1");
        ArrayList<Move> turn2 = new ArrayList<Move>();
        turn2.add(createMove(board, new Position(0,2), new Position(1,3)));
        turn2.add(createMove(board, new Position(0,0), new Position(0,1)));
        turn2.add(createAttackMove(board, new Position(1, 3), new Position(1, 2)));
        doIteration(gsm, game, turn2);
        prevTurn = turn2;
    }

    private void round1() {
        /////////////////////1. round /////////////////////
        ClientConnection.getInstance().setUsername("p2");
        ArrayList<Move> turn1 = new ArrayList<Move>();
        turn1.add(createMove(board, new Position(2, 2), new Position(1, 2)));
        doIteration(gsm, game, turn1);
        prevTurn = turn1;
    }

    private void doIteration(ScreenStateManager gsm, Game game, ArrayList<Move> turn) {
        ActiveGameScreen gameScreen = new ActiveGameScreen(gsm, game);
        gsm.pushState(gameScreen);


    }

    private MovementMove createMove(IBoard board, Position startCoordinate, Position endCoordinate) {
        return new MovementMove(board.getCell(startCoordinate), board.getCell(endCoordinate), movementController.getMovementPath(board, startCoordinate, endCoordinate));
    }
    private AttackMove createAttackMove(IBoard board, Position startCoordinate, Position endCoordinate) {
        return new AttackMove(board.getCell(startCoordinate), board.getCell(endCoordinate));
    }


    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {
       // timeSinceLast += 1;
        counter++;
        if(counter == 1) {
            round1();
        } else if(counter == 2) {
            game.setNextTurnIsPlayer1(!game.getNextTurnIsPlayer1());
            boardMover.executeMovesFromOtherPlayer(prevTurn, true);
            game.setLastMoves(prevTurn);
            System.out.println("About to start round 2");
            round2();
        } else if(counter == 3) {
            game.setNextTurnIsPlayer1(!game.getNextTurnIsPlayer1());
            boardMover.executeMovesFromOtherPlayer(prevTurn, true);
            game.setLastMoves(prevTurn);
            round3();
        } else {
            System.exit(0);
        }
    }

    @Override
    public void dispose() {

    }
}

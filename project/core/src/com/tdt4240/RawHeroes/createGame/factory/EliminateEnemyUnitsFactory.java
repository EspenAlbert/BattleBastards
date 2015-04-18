package com.tdt4240.RawHeroes.createGame.factory;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.createGame.games.EliminateAllEnemiesGame;
import com.tdt4240.RawHeroes.event.move.Move;
import com.tdt4240.RawHeroes.event.move.MovementMove;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.independent.Position;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;

import java.util.ArrayList;

/**
 * Created by espen1 on 07.03.2015.
 */
public class EliminateEnemyUnitsFactory {
    private static EliminateEnemyUnitsFactory instance;
    public static final String TYPE2= "2";

    public static EliminateEnemyUnitsFactory getInstance() {
        if(instance == null) {
            instance = new EliminateEnemyUnitsFactory();
        }
        return instance;
    }

    public Game createGame(String player1, String player2) {
        return new EliminateAllEnemiesGame(player1, player2);
    }

    public ArrayList<Move> createMoves(String type, IBoard board) {
        if(type.equals(TYPE2)) {
            ArrayList<Move> moves = new ArrayList<Move>();
            MovementMove move1 = getMovementMove(board, new Position(0,0), new Position(3,0), true);
            moves.add(move1);
            MovementMove move2 = getMovementMove(board, new Position(3,0), new Position(3,3), false);
            moves.add(move2);
            MovementMove move3 = getMovementMove(board, new Position(3,3), new Position(6,3), true);
            moves.add(move3);
            MovementMove move4 = getMovementMove(board, new Position(6,3), new Position(6,0), false);
            moves.add(move4);
            MovementMove move5 = getMovementMove(board, new Position(6,0), new Position(0,0), true);
            moves.add(move5);
            return moves;
        }
        return null;
    }

    private MovementMove getMovementMove(IBoard board, Position start, Position end, boolean xDirection) {
        Position moverPos = start.cpy();
        Position endPos = end;

        int difference = (int) (xDirection ? end.getX() - start.getX() : end.getY() - start.getY());
        int length  = Math.abs(difference);
        // ArrayList<Vector2> path = board.getCell(moverPos).getUnit().getMovementPath(board, moverPos, endPos); //TODO: NOT WORKING AT THE MOMENT
        ArrayList<Position> path = new ArrayList<Position>();
        path.add(start.cpy());
        for(int i = 0; i< length ; i++) {
            int dx = xDirection ? difference > 0 ? 1 : -1 : 0;
            int dy = xDirection ? 0 : difference > 0 ? 1 : -1;
            path.add(start.add(dx,dy).cpy());
        }
        return new MovementMove(board.getCell(moverPos), board.getCell(endPos), path);
    }
}

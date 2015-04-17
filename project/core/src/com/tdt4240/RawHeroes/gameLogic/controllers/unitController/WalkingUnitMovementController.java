package com.tdt4240.RawHeroes.gameLogic.controllers.unitController;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.cell.CellStatus;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.independent.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;


/**
 * Created by espen1 on 12.04.2015.
 */
public class WalkingUnitMovementController implements IUnitMovementController {
    private ArrayList<Position> directions;
    //TODO check if w is outside the board

    public WalkingUnitMovementController(){
        directions=new ArrayList<Position>();
        directions.add(new Position(0,1));
        directions.add(new Position(1,0));
        directions.add(new Position(0,-1));
        directions.add(new Position(-1,0));
    }

    @Override
    public ArrayList<Position> getMovementZone(IBoard board, Position myPos, int movesLeft, int unitMaxMoves) {
        int maxDepth=Math.min(movesLeft, unitMaxMoves); //max moves for unit
        System.out.println("Max depth should be 3, is " + maxDepth);

        //breadth first search
        ArrayList<Pair<Position, Integer>> queue=new ArrayList<Pair<Position, Integer>>();
        ArrayList<Position> discovered = new ArrayList<Position>();//
        queue.add(new Pair<Position, Integer>(myPos,0));
        int depth=0;
        //discovered.add(myPos);
        while ((!queue.isEmpty())&&(depth<maxDepth)){
            Position v=queue.remove(0).getKey();
            for (int i=0; i<directions.size();i++){
                Position w=new Position(v);
                w.add(directions.get(i));//v+directions.get(i)
                System.out.println("before if: "+w);
                if (w.getX()>=0&&w.getY()>=0&&w.getX()<(board.getWidth())&&w.getY()<board.getHeight()){
                    ICell cell=board.getCell(w);
                    if((!discovered.contains(w))&& (cell.getStatus()!= CellStatus.NOTMOVEABLE) && (cell.getUnit() == null)) {//not discovered and not notmoveable.
                        queue.add(new Pair<Position, Integer>(w, depth + 1));
                        discovered.add(w);
                    }
                }
            }
            depth=queue.get(0).getValue();
        }
        return discovered;
    }

    @Override
    //returns null if path isn't found
    public ArrayList<Position> getMovementPath(IBoard board, Position myPos, Position targetPos) {
        //uses breadth first search to find the shortest path
        ArrayList<Position> path=new ArrayList<Position>();//returns path
        ArrayList<Position> queue=new ArrayList<Position>();
        HashMap<Position, Position> discovered = new HashMap<Position, Position>();//<mypos, parent>
        queue.add(myPos);
        discovered.put(myPos, null);
        while (!queue.isEmpty()){
            Position v=queue.remove(0);
            for (int i=0;i<directions.size();i++){
                Position w=new Position(v);
                w.add(directions.get(i));//v+directions.get(i)
                if (w.getX()>=0&&w.getY()>=0&&w.getX()<(board.getWidth())&&w.getY()<board.getHeight()){
                    ICell cell=board.getCell(w);
                    if((!discovered.containsKey(w))&& (cell.getStatus() != CellStatus.NOTMOVEABLE) && (cell.getStatus() != CellStatus.SELECTED)) {//not discovered and not notmoveable.
                        if (w.getX()==targetPos.getX()&&w.getY()==targetPos.getY()) {
                            path.add(v);
                            path.add(w);//finds the path
                            while (true) {
                                if (discovered.get(v) == null) {
                                    return path;
                                }
                                v=discovered.get(v);
                                path.add(0, v);
                            }
                        }
                        queue.add(w);
                        discovered.put(w, v);
                    }
                }
            }
        }
        return null;
    }
}

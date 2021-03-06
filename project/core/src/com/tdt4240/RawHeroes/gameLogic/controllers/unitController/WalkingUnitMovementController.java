package com.tdt4240.RawHeroes.gameLogic.controllers.unitController;

import com.tdt4240.RawHeroes.gameLogic.cell.CellStatus;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.independent.Pair;
import com.tdt4240.RawHeroes.independent.Position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by espen1 on 12.04.2015.
 */
public class WalkingUnitMovementController implements IUnitMovementController{
    public final static long serialVersionUID = 987653454249623536l;
    private ArrayList<Position> directions;

    public WalkingUnitMovementController(){
        directions=new ArrayList<Position>();
        directions.add(new Position(0,1));
        directions.add(new Position(1,0));
        directions.add(new Position(0,-1));
        directions.add(new Position(-1,0));
    }

    @Override
    public ArrayList<Position> getMovementZone(IBoard board, Position myPos, int energyLeft, int movesLeft) {
        int maxDepth=Math.min(energyLeft/board.getCell(myPos).getUnit().getWeight(), movesLeft); //max moves for unit
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
                if (w.getX()>=0&&w.getY()>=0&&w.getX()<(board.getWidth())&&w.getY()<board.getHeight()){
                    ICell cell=board.getCell(w);
                    if((!discovered.contains(w))&& (cell.getStatus()!= CellStatus.NOTMOVEABLE) && (cell.getUnit() == null)) {//not discovered and not notmoveable.
                        queue.add(new Pair<Position, Integer>(w, depth + 1));
                        discovered.add(w);
                    }
                }
            }
            if(!queue.isEmpty()) depth=queue.get(0).getValue();
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
                    if((!discovered.containsKey(w))&& (cell.getStatus() != CellStatus.NOTMOVEABLE) && (cell.getStatus() != CellStatus.SELECTED) && (cell.getUnit() == null)) {//not discovered and not notmoveable.
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

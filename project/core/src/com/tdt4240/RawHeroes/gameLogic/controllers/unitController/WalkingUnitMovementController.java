package com.tdt4240.RawHeroes.gameLogic.controllers.unitController;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.cell.CellStatus;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by espen1 on 12.04.2015.
 */
public class WalkingUnitMovementController implements IUnitMovementController {
    private ArrayList<Vector2> directions;
    //TODO check if w is outside the board

    public WalkingUnitMovementController(){
        directions=new ArrayList<Vector2>();
        directions.add(new Vector2(0,1));
        directions.add(new Vector2(1,0));
        directions.add(new Vector2(0,-1));
        directions.add(new Vector2(-1,0));
    }

    @Override
    public ArrayList<Vector2> getMovementZone(IBoard board, Vector2 myPos, int movesLeft, int unitMaxMoves) {
        int maxDepth=Math.min(movesLeft, unitMaxMoves); //max moves for unit
        System.out.println("Max depth should be 3, is " + maxDepth);

        //breadth first search
        ArrayList<Pair<Vector2, Integer>> queue=new ArrayList<Pair<Vector2, Integer>>();
        ArrayList<Vector2> discovered = new ArrayList<Vector2>();//
        queue.add(new Pair<Vector2, Integer>(myPos,0));
        int depth=0;
        discovered.add(myPos);
        while ((!queue.isEmpty())&&(depth<maxDepth)){
            Vector2 v=queue.remove(0).getKey();
            for (int i=0; i<directions.size();i++){
                Vector2 w=new Vector2(v);
                w.add(directions.get(i));//v+directions.get(i)
                System.out.println("before if: "+w);
                if (w.x>=0&&w.y>=0&&w.x<(board.getWidth())&&w.y<board.getHeight()){
                    ICell cell=board.getCell(w);
                    if((!discovered.contains(w))&&(!(cell.getStatus()== CellStatus.NOTMOVEABLE))) {//not discovered and not notmoveable.
                        queue.add(new Pair<Vector2, Integer>(w, depth + 1));
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
    public ArrayList<Vector2> getMovementPath(IBoard board, Vector2 myPos, Vector2 targetPos) {
        //uses breadth first search to find the shortest path
        ArrayList<Vector2> path=new ArrayList<Vector2>();//returns path
        ArrayList<Vector2> queue=new ArrayList<Vector2>();
        HashMap<Vector2, Vector2> discovered = new HashMap<Vector2, Vector2>();//<mypos, parent>
        queue.add(myPos);
        discovered.put(myPos, null);
        while (!queue.isEmpty()){
            Vector2 v=queue.remove(0);
            for (int i=0;i<directions.size();i++){
                Vector2 w=new Vector2(v);
                w.add(directions.get(i));//v+directions.get(i)
                if (w.x>=0&&w.y>=0&&w.x<(board.getWidth())&&w.y<board.getHeight()){
                    ICell cell=board.getCell(w);
                    if((!discovered.containsKey(w))&&(!(cell.getStatus()== CellStatus.NOTMOVEABLE))) {//not discovered and not notmoveable.
                        if (w.x==targetPos.x&&w.y==targetPos.y) {
                            path.add(w);//finds the path
                            path.add(v);
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

package com.tdt4240.RawHeroes.event.move;

import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CellConverter;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.independent.Position;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by espen1 on 27.02.2015.
 */
public class AttackMove extends Move {

    private HashMap<Position, Integer> damages;


    public AttackMove(ICell selectedCell, ICell target) {
        super(selectedCell, target);
        this.damages = new HashMap<Position, Integer>();
        if (getTargetCell().getPos().getX() < getStartCell().getPos().getX() && this.getStartCell().getUnit().isTurnedRight()){
            this.getStartCell().getUnit().turnDirection();
        }else if (!this.getStartCell().getUnit().isTurnedRight()) this.getStartCell().getUnit().turnDirection();
    }


    @Override
    public int getCost(){
        return this.getStartCell().getUnit().getWeight();
    }
    @Override
    public void execute(IBoard board) {
        setStartCell(board.getCell(getStartCell().getPos()));
        setTargetCell(board.getCell(getTargetCell().getPos()));
        if(!getStartCell().getUnit().hasAttacked()) { //First time execute is called
            getStartCell().getUnit().setHasAttacked(true);
            return;
        }
        initializeDamages(board);
        for (Position key : damages.keySet()) {
                board.getCell(key).getUnit().attacked(damages.get(key));
            }
    }

    @Override
    public void undo(IBoard board) {
        getStartCell().getUnit().setHasAttacked(false);
    }

    @Override
    public void convertPositions(int boardWidth, int boardHeight) {
        HashMap<Position, Integer> newDamages = new HashMap<Position, Integer>();
        for(Position pos : damages.keySet()) {
            newDamages.put(CellConverter.switchPosition(pos, boardWidth, boardHeight), damages.get(pos));
        }
        damages = newDamages;
    }

    private void initializeDamages(IBoard board) {
        Position attackerPos = getStartCell().getPos();
        Position targetPos = getTargetCell().getPos();
        IUnit attacker = board.getCell(attackerPos).getUnit();
        ArrayList<Position> inflictionZone = attacker.getInflictionZone(attackerPos, targetPos);
        for (Position victimPos : inflictionZone) {
            IUnit victim = board.getCell(victimPos).getUnit();
            if (victim != null) {
                int dmg = attacker.inflictDamage(attackerPos, targetPos);
                damages.put(victimPos, dmg);
            }
        }
    }

    public Iterable<Position> getVictims(){
        return damages.keySet();
    }
}
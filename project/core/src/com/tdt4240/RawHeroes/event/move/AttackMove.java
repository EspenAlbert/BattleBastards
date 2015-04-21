package com.tdt4240.RawHeroes.event.move;

import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CellConverter;
import com.tdt4240.RawHeroes.independent.Pair;
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
    private Pair<Position, Position> originalPosition;


    public AttackMove(ICell selectedCell, ICell target) {
        super(selectedCell, target);
        this.damages = new HashMap<Position, Integer>();
        originalPosition = new Pair<Position, Position>(new Position(selectedCell.getPos().getX(), selectedCell.getPos().getY()),new Position(target.getPos().getX(), target.getPos().getY()));
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
            System.out.println("Attacking: " + key.getX() + "," + key.getY() +  " with damage: " + damages.get(key));
            board.getCell(key).getUnit().attacked(damages.get(key));
            if(board.getCell(key).getUnit().getHealth() < 1) board.getCell(key).setUnit(null);
            }
    }

    @Override
    public void undo(IBoard board) {
        getStartCell().getUnit().setHasAttacked(false);
    }


    @Override
    public void convertPositions(CellConverter converter) {
        HashMap<Position, Integer> newDamages = new HashMap<Position, Integer>();
        for(Position pos : damages.keySet()) {
            newDamages.put(converter.switchPosition(pos), damages.get(pos));
        }
        getStartCell().setPos(converter.switchPosition(getStartCell().getPos()));
        if(getStartCell().getPos().equals(originalPosition.getKey())) getStartCell().setPos(converter.switchPosition(getStartCell().getPos()));
        getTargetCell().setPos(converter.switchPosition(getTargetCell().getPos()));
        if(getTargetCell().getPos().equals(originalPosition.getValue())) getTargetCell().setPos(converter.switchPosition(getTargetCell().getPos()));
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
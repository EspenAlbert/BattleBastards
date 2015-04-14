package com.tdt4240.RawHeroes.gameLogic.controllers.boardController;

import com.tdt4240.RawHeroes.event.move.AttackMove;
import com.tdt4240.RawHeroes.gameLogic.cell.CellStatus;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

import java.util.ArrayList;

/**
 * Created by espen1 on 28.02.2015.
 */
public class BoardControllerCellAndAttackSelectedState extends BoardControllerState {
    private ICell selectedCell;
    private ArrayList<ICell> attackableCells;
    public BoardControllerCellAndAttackSelectedState(IBoardController boardController, IBoard board, ICell cell) {
        super(boardController, board);
        selectedCell = cell;
        selectedCell.setStatus(CellStatus.SELECTED);
        attackableCells = new ArrayList<ICell>();
        //TODO sette attackable status på celler som kan attackes basert på unit, f.eks.:
        /*if(cell.getUnit().getType() == "MELEE"){
            for (int x = selectedCell.getPos.getX()-1; x <= selectedCell.getPos.getX()+1; x++{
                for (int y = selectedCell.getPos.getY()-1; y <= selectedCell.getPos.getY()+1; y++{
                    this.board.getCells()[x][y].setStatus(CellStatus.ATTACKABLE)
                }
            }
         */
    }

    @Override
    public void actionButtonPressed() {
        //TODO forandre knappen, må finne ut av hvordan
        this.boardController.setState(new BoardControllerCellSelectedState(this.boardController, this.board, this.selectedCell));
    }

    @Override
    public void cellSelected(ICell cell) {
        if (cell.getStatus() == CellStatus.ATTACKABLE){
            //TODO sjekke om man har nok energi før movet gjøres
            this.boardController.addMove(new AttackMove(selectedCell, cell));
            //TODO disable så samme unit ikke kan angripe flere ganger per tur
        }
        //TODO også ha tilbakegåing til NoCellSelectedState hvis man trykker på en default

    }

    @Override
    public void popped() {
        for (ICell cell : attackableCells){
            //TODO må sjekke side, altså om uniten er ens egen
                if (cell.getUnit() == null) cell.setStatus(CellStatus.DEFAULT);
                else cell.setStatus(CellStatus.SELECTABLE);
            }
    }
}

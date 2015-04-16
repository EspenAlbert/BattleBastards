package com.tdt4240.RawHeroes.gameLogic.controllers.boardController;

/**
 * Created by Endre on 16.04.2015.
 */
public class BoardControllerStateEvent {

    private int energy;
    private String actionButtonText;

    public int getEnergy() {
        return energy;
    }

    public String getActionButtonText() {
        return actionButtonText;
    }

    public BoardControllerStateEvent(int energy, String actionButtonText){
        this.energy = energy;
        this.actionButtonText = actionButtonText;

    }
}

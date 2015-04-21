package com.tdt4240.RawHeroes.view.customUIElements.finishScreenRenderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.tdt4240.RawHeroes.gameLogic.inputListeners.TouchListenerActiveGameScreen;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.IRenderNoPos;
import com.tdt4240.RawHeroes.view.uiElements.DialogFactory;
import com.tdt4240.RawHeroes.view.uiElements.LabelFactory;
import com.tdt4240.RawHeroes.view.uiElements.MainMenuButtonsFactory;

/**
 * Created by espen1 on 21.04.2015.
 */
public class FinishScreenRenderer implements IRenderNoPos {

    public static String[] noOptionMessages = {"It is not your turn to do a move!", "You successfully did moves to game: "};

    private TextButton cancelButton;
    private TextButton acceptButton;
    private Stage stage;
    private Dialog dialog;
    private Label label;

    public static final int DIALOG_WIDTH = Math.round(TouchListenerActiveGameScreen.getBoardMaxXCoordinate());
    public static final int DIALOG_HEIGHT = GameConstants.RESOLUTION_HEIGHT;
    public static final int LABEL_HEIGHT = GameConstants.RESOLUTION_HEIGHT / 4;
    public static final int LABEL_WIDTH = DIALOG_WIDTH;
    public static final int DIALOG_ELEMENTS_MARGIN = 10;
    public static final int LABEL_Y = DIALOG_HEIGHT - LABEL_HEIGHT;
    public static final int BUTTON_Y = 0;
    public static final int BUTTON_XSIZE = (DIALOG_WIDTH-DIALOG_ELEMENTS_MARGIN) / 2;
    public static final int BUTTON_YSIZE = DIALOG_HEIGHT - LABEL_HEIGHT;

    public FinishScreenRenderer(String message) {
        System.out.println("New finish screen renderer");
        stage = new Stage();
        dialog = DialogFactory.createDialogFactory("FINISH SCREEN", 0, 0, DIALOG_WIDTH, DIALOG_HEIGHT);
        if(isNoOptionMessage(message)) {
            acceptButton = MainMenuButtonsFactory.createButton("CONFIRM", DIALOG_ELEMENTS_MARGIN, BUTTON_Y, BUTTON_XSIZE*2, BUTTON_YSIZE);
        } else {
            acceptButton = MainMenuButtonsFactory.createButton("CONFIRM", DIALOG_ELEMENTS_MARGIN, BUTTON_Y, BUTTON_XSIZE, BUTTON_YSIZE);
            cancelButton = MainMenuButtonsFactory.createButton("CANCEL", DIALOG_ELEMENTS_MARGIN + BUTTON_XSIZE, BUTTON_Y, BUTTON_XSIZE, BUTTON_YSIZE);
            dialog.addActor(cancelButton);
            message += "?";

        }
        label = LabelFactory.createLabel(message, DIALOG_ELEMENTS_MARGIN, LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
        dialog.addActor(label);
        dialog.addActor(acceptButton);

        stage.addActor(dialog);
    }

    public static boolean isNoOptionMessage(String message) {
        for(String msg: noOptionMessages) {
            if(message.contains(msg)) return true;
        }
        return false;
    }

    @Override
    public void render(SpriteBatch batch) {
        stage.draw();

    }
}

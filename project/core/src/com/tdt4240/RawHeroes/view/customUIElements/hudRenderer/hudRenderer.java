package com.tdt4240.RawHeroes.view.customUIElements.hudRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.BoardControllerStateEvent;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.BoardControllerStateListener;
import com.tdt4240.RawHeroes.gameLogic.controllers.boardController.IBoardController;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.IRenderNoPos;

/**
 * Created by Endre on 16.04.2015.
 */

public class HudRenderer implements IRenderNoPos, BoardControllerStateListener {

    private Skin skin;

    private TextButton sendButton;
    private TextButton actionButton;
    private TextButton abortButton;

    private Label energyLabel;

    public HudRenderer(IBoardController boardController){
        setupUiElements();
        boardController.addBoardControllerStateListener(this);
    }
    @Override
    public void stateChanged(BoardControllerStateEvent event) {
        this.actionButton.setText(event.getActionButtonText());
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        sendButton.draw(batch, 1);
        abortButton.draw(batch, 1);
        actionButton.draw(batch, 1);
        energyLabel.draw(batch, 1);
        batch.end();
    }

    private void setupUiElements() {
        skin = new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas(Gdx.files.internal("uiskin.atlas")));


        int buttonWidth = GameConstants.RESOLUTION_WIDTH - 7*GameConstants.CELL_WIDTH;
        int buttonHeight = GameConstants.RESOLUTION_HEIGHT /4;

        sendButton = new TextButton("Done", skin);
        sendButton.setSize(buttonWidth, buttonHeight);
        actionButton= new TextButton("Action", skin);
        actionButton.setSize(buttonWidth, buttonHeight);
        abortButton= new TextButton("Quit", skin);
        abortButton.setSize(buttonWidth, buttonHeight);
        energyLabel = new Label("47/100",skin);
        energyLabel.setSize(buttonWidth, buttonHeight);
        energyLabel.setAlignment(0);
        energyLabel.setColor(1, 1, 1, 1);


        sendButton.setPosition(GameConstants.RESOLUTION_WIDTH-sendButton.getWidth(), abortButton.getHeight() + actionButton.getHeight());
        actionButton.setPosition(GameConstants.RESOLUTION_WIDTH - actionButton.getWidth(), abortButton.getHeight());
        abortButton.setPosition(GameConstants.RESOLUTION_WIDTH-abortButton.getWidth(), 0);
        energyLabel.setPosition(GameConstants.RESOLUTION_WIDTH-energyLabel.getWidth(), abortButton.getHeight() + actionButton.getHeight() + sendButton.getHeight());
    }
}

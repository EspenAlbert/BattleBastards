package com.tdt4240.RawHeroes.view.customUIElements.unitDetailRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.independent.Position;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.IRenderNoPos;

/**
 * Created by William on 18.04.2015.
 */
public class UnitDetailRenderer implements IRenderNoPos {

    private Stage stage;
    private Dialog createUnitDetailsScreenDialog;
    private Skin skin;
    private boolean hide;
    private IBoard board;

    public UnitDetailRenderer(IBoard board){
        this.board = board;
        stage = new Stage();
        hide = true;
        skin = new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas(Gdx.files.internal("uiskin.atlas")));

        System.out.println("Clicked a unit for detailed information...");
        createUnitDetailsScreenDialog = new Dialog("Unit Details", skin);
        createUnitDetailsScreenDialog.setBounds(50, 25, GameConstants.RESOLUTION_WIDTH - 200, GameConstants.RESOLUTION_HEIGHT - 50);
    }



    @Override
    public void render(SpriteBatch batch) {
        if (!hide){
            stage.addActor(createUnitDetailsScreenDialog);
            stage.draw();
        }
    }

    public void hideUnitDetails(){
        hide = true;
    }

    public void showUnitDetails(Position pos){
        if (board.getCell(pos).getUnit() != null){
            updateDialog(board.getCell(pos).getUnit());
            hide = false;
        }
    }

    private void updateDialog(IUnit unit) {
        final TextField textFieldUnit = new TextField(String.valueOf(unit.getIdentifier()), skin);
        textFieldUnit.setSize(GameConstants.TEXTFIELD_WIDTH, GameConstants.TEXTFIELD_HEIGHT);
        textFieldUnit.setPosition(createUnitDetailsScreenDialog.getWidth()/2 - textFieldUnit.getWidth()/4, createUnitDetailsScreenDialog.getHeight()/4);
        createUnitDetailsScreenDialog.addActor(textFieldUnit);
    }
}

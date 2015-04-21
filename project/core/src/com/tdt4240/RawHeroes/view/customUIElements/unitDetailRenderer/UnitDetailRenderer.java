package com.tdt4240.RawHeroes.view.customUIElements.unitDetailRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.independent.Position;
import com.tdt4240.RawHeroes.independent.TextureChanger;
import com.tdt4240.RawHeroes.view.customUIElements.unitRenderer.specificUnitRenderer.howToUse.IRenderNoPos;

import javax.xml.soap.Text;

/**
 * Created by William on 18.04.2015.
 */
public class UnitDetailRenderer implements IRenderNoPos {

    private Stage stage;
    private Dialog createUnitDetailsScreenDialog;
    private Skin skin;
    private boolean hide;
    private IBoard board;
    private Sprite sprite;

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
        Label labelUnit = new Label(String.valueOf(unit.getIdentifier()), skin);
        labelUnit.setSize(GameConstants.TEXTFIELD_WIDTH, GameConstants.TEXTFIELD_HEIGHT);
        labelUnit.setPosition(createUnitDetailsScreenDialog.getWidth()/8, createUnitDetailsScreenDialog.getHeight()*6/8);
        Texture texture;
        //Image
        switch (unit.getIdentifier()) {
            case STANDARD_UNIT:
                texture = new Texture(Gdx.files.internal("units/soldierSheet.png"));
                break;
            case STANDARD_UNIT_2:
                //TODO Fyll inn riktig .png-fil under
                texture = new Texture(Gdx.files.internal("units/soldierSheet.png"));
                break;
            case UNIT2:
                //TODO Fyll inn riktig .png-fil under
                texture = new Texture(Gdx.files.internal("units/soldierSheet.png"));
                break;
            default:
                texture = new Texture(Gdx.files.internal("units/soldierSheet.png"));
        }
        if (unit.isPlayer1Unit()){
            sprite = new Sprite(unit.getActiveFrame(TextureChanger.changeColor(texture, Color.RED)));
        } else {
            sprite = new Sprite(unit.getActiveFrame(TextureChanger.changeColor(texture, Color.BLUE)));
        }
        Image img = new Image(sprite);
        img.setSize(createUnitDetailsScreenDialog.getHeight()*3/8, createUnitDetailsScreenDialog.getHeight()/2);
        img.setPosition(createUnitDetailsScreenDialog.getWidth()/8, createUnitDetailsScreenDialog.getHeight()*2/8);

        //Labels
        int col2 = (int) createUnitDetailsScreenDialog.getWidth()*1/2;
        Label labelHealth = new Label("Health:", skin);
        labelHealth.setSize(GameConstants.LABEL_WIDTH, GameConstants.LABEL_HEIGHT);
        labelHealth.setPosition(col2, createUnitDetailsScreenDialog.getHeight()*4/7);
        Label labelAttack = new Label("Damage:", skin);
        labelAttack.setSize(GameConstants.LABEL_WIDTH, GameConstants.LABEL_HEIGHT);
        labelAttack.setPosition(col2, createUnitDetailsScreenDialog.getHeight()*3/7);
        Label labelArmor = new Label("Armor:", skin);
        labelArmor.setSize(GameConstants.LABEL_WIDTH, GameConstants.LABEL_HEIGHT);
        labelArmor.setPosition(col2, createUnitDetailsScreenDialog.getHeight()*2/7);

        //Values
        int col3 = (int) createUnitDetailsScreenDialog.getWidth()*4/6;
        TextField textFieldHealth = new TextField(String.valueOf(unit.getHealth()) + "/" + String.valueOf(unit.getMaxHealth()), skin);
        textFieldHealth.setSize(GameConstants.TEXTFIELD_WIDTH/2, GameConstants.TEXTFIELD_HEIGHT);
        textFieldHealth.setAlignment(Align.center);
        textFieldHealth.setPosition(col3, createUnitDetailsScreenDialog.getHeight()*4/7);

        int[] attackDmg = unit.getAttackDmg();
        TextField textFieldAttack = new TextField(String.valueOf(attackDmg[0]) + "-" + String.valueOf(attackDmg[1]), skin);
        textFieldAttack.setSize(GameConstants.TEXTFIELD_WIDTH/2, GameConstants.TEXTFIELD_HEIGHT);
        textFieldAttack.setAlignment(Align.center);
        textFieldAttack.setPosition(col3, createUnitDetailsScreenDialog.getHeight()*3/7);

        TextField textFieldArmor = new TextField(String.valueOf(unit.getArmor()), skin);
        textFieldArmor.setSize(GameConstants.TEXTFIELD_WIDTH/2, GameConstants.TEXTFIELD_HEIGHT);
        textFieldArmor.setAlignment(Align.center);
        textFieldArmor.setPosition(col3, createUnitDetailsScreenDialog.getHeight()*2/7);

        createUnitDetailsScreenDialog.clear();

        createUnitDetailsScreenDialog.addActor(labelUnit);
        createUnitDetailsScreenDialog.addActor(labelHealth);
        createUnitDetailsScreenDialog.addActor(labelAttack);
        createUnitDetailsScreenDialog.addActor(labelArmor);
        createUnitDetailsScreenDialog.addActor(textFieldHealth);
        createUnitDetailsScreenDialog.addActor(textFieldAttack);
        createUnitDetailsScreenDialog.addActor(textFieldArmor);
        createUnitDetailsScreenDialog.addActor(img);
    }
}

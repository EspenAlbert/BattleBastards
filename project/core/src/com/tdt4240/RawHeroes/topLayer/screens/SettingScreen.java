package com.tdt4240.RawHeroes.topLayer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tdt4240.RawHeroes.independent.Encryption;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.network.client.ClientConnection;
import com.tdt4240.RawHeroes.view.uiElements.DialogFactory;
import com.tdt4240.RawHeroes.view.uiElements.LabelFactory;
import com.tdt4240.RawHeroes.view.uiElements.MainMenuButtonsFactory;
import com.tdt4240.RawHeroes.view.uiElements.TextFieldFactory;

import java.security.NoSuchAlgorithmException;

/**
 * Created by Håvard on 19.04.2015.
 */
public class SettingScreen extends ScreenState {
    private Stage stage;
    private Label title;
    private final TextButton changePasswordButton;



    public SettingScreen(ScreenStateManager gsm) {
        super(gsm);

        stage = new Stage();
        changePasswordButton = MainMenuButtonsFactory.createButton("Change password", GameConstants.RESOLUTION_WIDTH/2, GameConstants.RESOLUTION_HEIGHT/2);

        changePasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changePasswordButtonClicked();
            }
        });
        Gdx.input.setInputProcessor(stage);
        stage.addActor(changePasswordButton);
        //stage.addActor(title);
    }
    public void changePasswordButtonClicked(){
        System.out.println("clicked create game...");
        final Dialog createGameDialog = DialogFactory.createDialogFactory("Create game");

        final TextField oldPasswordTextField = TextFieldFactory.createTextField("Old password", (int) createGameDialog.getWidth() / 2 - GameConstants.RESOLUTION_WIDTH / 10, (int) createGameDialog.getHeight() / 2 + GameConstants.RESOLUTION_HEIGHT/7, false);
        final TextField newPasswordTextField = TextFieldFactory.createTextField("New password", (int) createGameDialog.getWidth() / 2 - GameConstants.RESOLUTION_WIDTH / 10, (int) createGameDialog.getHeight() / 2, false);
        final TextField confirmPasswordTextField = TextFieldFactory.createTextField("Confirm password", (int) createGameDialog.getWidth() / 2 - GameConstants.RESOLUTION_WIDTH / 10, (int) createGameDialog.getHeight() / 2 - GameConstants.RESOLUTION_HEIGHT/7, false);
        TextButton changePasswordButton = MainMenuButtonsFactory.createButton("Change password",GameConstants.RESOLUTION_WIDTH*2/3 -GameConstants.RESOLUTION_WIDTH/10, GameConstants.RESOLUTION_HEIGHT/10);
        changePasswordButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                String pwd;
                System.out.println(ClientConnection.getInstance().getPassword());
                try {
                    pwd = Encryption.encrypt(oldPasswordTextField.getText());
                }catch (NoSuchAlgorithmException exception){
                    pwd = oldPasswordTextField.getText();
                }
                if(pwd.equals(ClientConnection.getInstance().getPassword())) { //TODO: Get back the real password(not encrypted)
                    if(confirmPasswordTextField.getText().equals(newPasswordTextField.getText())) {
                        changePasswordDialogButtonClicked(newPasswordTextField.getText());
                        createGameDialog.hide();
                    }
                    else{
                        title.setText("The new password did not match the confirmed one");
                    }
                }
                else{
                    title.setText("The old password was incorrect");
                }
            }
        });

        TextButton buttonBackToMainMenu = MainMenuButtonsFactory.createButton("Back", GameConstants.RESOLUTION_WIDTH/4 -GameConstants.RESOLUTION_WIDTH/10, GameConstants.RESOLUTION_HEIGHT/10);
        buttonBackToMainMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                createGameDialog.remove();
            }
        });
        final Label oldPasswordLabel = LabelFactory.createLabel("Old password", (int)(oldPasswordTextField.getX() - oldPasswordTextField.getWidth()/2), (int)oldPasswordTextField.getY());
        final Label newPasswordLabel = LabelFactory.createLabel("New password", (int)(newPasswordTextField.getX() - newPasswordTextField.getWidth()/2), (int)newPasswordTextField.getY());
        final Label confirmPasswordLabel = LabelFactory.createLabel("Confirm password", (int)(confirmPasswordTextField.getX() - confirmPasswordTextField.getWidth()*2/3), (int)confirmPasswordTextField.getY());

        title = LabelFactory.createLabel(" ", (int)createGameDialog.getWidth()/2 - GameConstants.RESOLUTION_WIDTH/10, (int)createGameDialog.getHeight() - GameConstants.RESOLUTION_HEIGHT/7 );

        createGameDialog.addActor(title);
        createGameDialog.addActor(oldPasswordLabel);
        createGameDialog.addActor(newPasswordLabel);
        createGameDialog.addActor(confirmPasswordLabel);

        createGameDialog.addActor(buttonBackToMainMenu);
        createGameDialog.addActor(changePasswordButton);

        createGameDialog.addActor(oldPasswordTextField);
        createGameDialog.addActor(newPasswordTextField);
        createGameDialog.addActor(confirmPasswordTextField);
        stage.addActor(createGameDialog);
    }
    public void changePasswordDialogButtonClicked(String password){
        ClientConnection.getInstance().setPassword(password);
    }



    @Override
    public void update(float dt) {
        Gdx.gl.glClearColor(0.36f, 0.32f, 0.27f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        stage.act();
        stage.draw();
        spriteBatch.end();
    }

    @Override
    public void render() {

    }

    @Override
    public void dispose() {

    }
}

package com.tdt4240.RawHeroes.topLayer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tdt4240.RawHeroes.independent.Encryption;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.network.client.ClientConnection;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseMessage;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseType;
import com.tdt4240.RawHeroes.view.uiElements.LabelFactory;
import com.tdt4240.RawHeroes.view.uiElements.MainMenuButtonsFactory;
import com.tdt4240.RawHeroes.view.uiElements.TextFieldFactory;

import java.security.NoSuchAlgorithmException;

/**
 * Created by espen1 on 27.02.2015.
 */
public class LoginScreen extends ScreenState {
    private final Texture img;
    private final CheckBox checkBoxNewuser;
    private final Label labelInstruction;
    private final Label failedLogInLabel;
    private Stage stage;
    private boolean failedToLogIn;
    private TextButton buttonPlay, buttonLogin;
    private TextField textFieldUsername;
    private TextField textFieldPassword;
    private Label title;
    private int loginAttempts;



    protected LoginScreen(ScreenStateManager gsm) {
        super(gsm);
        img = new Texture("badlogic.jpg");
        //skin = new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
        stage = new Stage();
        title = new Label("Game Title",skin);
        loginAttempts = 0;



        int xPos = GameConstants.RESOLUTION_WIDTH/2 - GameConstants.BUTTON_WIDTH/2;
        int scaleY = GameConstants.RESOLUTION_HEIGHT/6;
        int yPosTitle = GameConstants.RESOLUTION_HEIGHT - scaleY;
        int yPosLabelInstruction = yPosTitle - scaleY;
        int yPosUserName = yPosLabelInstruction - scaleY;
        int yPosPassword = yPosUserName - scaleY;
        int yPosButton = yPosPassword - scaleY;


        buttonLogin = MainMenuButtonsFactory.createButton("Login", xPos, yPosButton);
        //buttonLogin.getStyle().font.setScale((float)GameConstants.BUTTON_WIDTH*4/(float)GameConstants.RESOLUTION_WIDTH,(float)GameConstants.BUTTON_WIDTH*4/(float)GameConstants.RESOLUTION_WIDTH);
        buttonLogin.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                loginButtonClicked();
            }
        });
        textFieldUsername = TextFieldFactory.createTextField("username", xPos, yPosUserName, false);
        textFieldPassword = TextFieldFactory.createTextField("password", xPos, yPosPassword, false);
        textFieldPassword.setPasswordCharacter('x');
        textFieldPassword.setPasswordMode(true);


        labelInstruction = LabelFactory.createLabel("Please specify username and password", xPos, yPosLabelInstruction);
        int xPosCheckBox = xPos - (int)buttonLogin.getWidth()/2;
        title.setPosition(xPos, yPosTitle);
        title.setSize(GameConstants.LABEL_WIDTH,GameConstants.LABEL_HEIGHT);

        checkBoxNewuser = new CheckBox("New user", skin);
        checkBoxNewuser.getCells().get(0).size(GameConstants.BUTTON_HEIGHT, GameConstants.BUTTON_HEIGHT);
        checkBoxNewuser.setPosition(xPosCheckBox - checkBoxNewuser.getWidth(), yPosButton);


        stage.addActor(labelInstruction);
        stage.addActor(title);
        stage.addActor(checkBoxNewuser);
        stage.addActor(textFieldUsername);
        stage.addActor(textFieldPassword);
        stage.addActor(buttonLogin);
        Gdx.input.setInputProcessor(stage);

        System.out.println("In loginScreen");
        //Create textfield for login and password
        // Create login button with listener

        failedLogInLabel = LabelFactory.createLabel("FETTNOOB", GameConstants.RESOLUTION_WIDTH/2, GameConstants.RESOLUTION_HEIGHT/2);

    }


    public void loginButtonClicked() {
        String username = textFieldUsername.getText();
        String pwd;
        try {
            pwd = Encryption.encrypt(textFieldPassword.getText());
        }catch (NoSuchAlgorithmException exception){
            pwd = textFieldPassword.getText();
        }

        ClientConnection clientConnection = ClientConnection.getInstance();
        ResponseMessage response;
        if(checkBoxNewuser.isChecked()) response = clientConnection.createUser(username, pwd);
        else response = clientConnection.login(username, pwd);
        ResponseType type = response.getType();
        if (loginAttempts > 4){
            failedToLogIn = true;
        } else {
            if(type.equals(ResponseType.SUCCESS)) {
                ClientConnection.getInstance().setUsername(username);
                ClientConnection.getInstance().setPassword(pwd);
                System.out.println("Success, login successfull");
                gsm.setState(ScreenStateManager.MAINMENU);
            }
            else {
                String errorMessage = (String) response.getContent();
                labelInstruction.setText(errorMessage);
                System.out.println("Failed to login, errormessage: " + errorMessage);
                System.out.println("You have " + Integer.toString(5-loginAttempts) + " login attempts left");
                loginAttempts ++;
            }
        }
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {

		Gdx.gl.glClearColor(0.36f, 0.32f, 0.27f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        if(failedToLogIn){
            failedLogInLabel.draw(spriteBatch, 100);
        }else {
            stage.act();
            stage.draw();
        }
        spriteBatch.draw(img, 0, 0);
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

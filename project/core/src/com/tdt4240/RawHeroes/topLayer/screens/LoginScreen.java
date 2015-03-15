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
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.network.client.ClientConnection;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseMessage;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseType;

/**
 * Created by espen1 on 27.02.2015.
 */
public class LoginScreen extends ScreenState {
    private final Texture img;
    private final CheckBox checkBoxNewuser;
    private final Label labelInstruction;
    private Stage stage;
   // private TextureAtlas atlas;
    private Skin skin;
    private TextButton buttonPlay, buttonLogin;
    private TextField textFieldUsername;
    private TextField textFieldPassword;
    private Label title;


    protected LoginScreen(ScreenStateManager gsm) {
        super(gsm);
        img = new Texture("badlogic.jpg");
        skin = new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
        stage = new Stage();
        title = new Label("Game Title",skin);

        int xPos = 200;

        buttonLogin = new TextButton("Login", skin);
        buttonLogin.setPosition(xPos, 150);
        buttonLogin.setSize(300, 60);
        buttonLogin.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                loginButtonClicked();
            }
        });
        textFieldUsername = new TextField("username", skin);
        textFieldUsername.setPosition(xPos, 300);
        textFieldUsername.setSize(300, 40);

        textFieldPassword = new TextField("password", skin);
        textFieldPassword.setPosition(xPos, 250);
        textFieldPassword.setSize(300, 40);
        textFieldPassword.setPasswordCharacter('x');
        textFieldPassword.setPasswordMode(true);

        checkBoxNewuser = new CheckBox("New user", skin);
        checkBoxNewuser.setPosition(xPos-100, 150);
        checkBoxNewuser.setSize(100,50);

        labelInstruction = new Label("Please specify username and password", skin);
        labelInstruction.setPosition(xPos,400);
        labelInstruction.setSize(300,40);
        title.setPosition(xPos, GameConstants.RESOLUTION_HEIGHT - 100);
        title.setSize(100,50);


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


    }


    public void loginButtonClicked() {
        String username = textFieldUsername.getText();
        String pwd = textFieldPassword.getText();
        ResponseMessage response;
        if(checkBoxNewuser.isChecked()) response = clientConnection.createUser(username, pwd);
        else response = clientConnection.login(username, pwd);
        ResponseType type = response.getType();
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
        stage.act();
        stage.draw();
        spriteBatch.draw(img, 0, 0);
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

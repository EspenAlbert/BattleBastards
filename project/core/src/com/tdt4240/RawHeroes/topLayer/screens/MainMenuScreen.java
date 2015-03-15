package com.tdt4240.RawHeroes.topLayer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tdt4240.RawHeroes.mainMenuGamesHandler.clientGameState.ClientGameState;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseMessage;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseType;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;
import com.tdt4240.RawHeroes.network.client.ClientConnection;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Games;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public class MainMenuScreen extends ScreenState {
    private final Skin skin;
    private final Stage stage;
    private final TextButton buttonCreateGame;
    private final Label labelInstruction;
    private final TextField textFieldGetGame;
    private final TextButton buttonGetGame;
    private ArrayList<ClientGameState> games;

    public MainMenuScreen(ScreenStateManager gsm) {
        super(gsm);
        System.out.println("Created main menu screen");
        games = new ArrayList<ClientGameState>();
        //int[] myGames = ClientConnection.getInstance().getMyGames();
        //MainMenuView view = new MainMenuView(myGames);
        skin = new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
        stage = new Stage();

        buttonCreateGame = new TextButton("CreateGame", skin);
        buttonCreateGame.setPosition(200, 150);
        buttonCreateGame.setSize(300, 60);
        labelInstruction = new Label("", skin);
        labelInstruction.setSize(100, 40);
        labelInstruction.setPosition(300, 250);

        textFieldGetGame = new TextField("gameId", skin);
        textFieldGetGame.setSize(300, 60);
        textFieldGetGame.setPosition(200, 80);
        textFieldGetGame.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());

        buttonGetGame = new TextButton("getGameById", skin);
        buttonGetGame.setPosition(200, 20);
        buttonGetGame.setSize(300, 60);

        buttonCreateGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                createGameButtonClicked();
            }
        });
        buttonGetGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getGameButtonClicked();
            }
        });


        Gdx.input.setInputProcessor(stage);
        stage.addActor(buttonCreateGame);
        stage.addActor(labelInstruction);
        stage.addActor(textFieldGetGame);
        stage.addActor(buttonGetGame);

    }

    private void getGameButtonClicked() {
        int gameid = Integer.parseInt(textFieldGetGame.getText());
        ResponseMessage responseMessage = ClientConnection.getInstance().getGame(gameid);
        if(responseMessage.getType() == ResponseType.FAILURE) {
            labelInstruction.setText((CharSequence) responseMessage.getContent());
        } else {
            Game game = (Game) responseMessage.getContent();
            System.out.println("managed to get game: " + game.getId() + " with players: " + game.getPlayer1Nickname() + " player 2:" + game.getPlayer2Nickname());
            gsm.setState(new ActiveGameScreen(gsm, game));
        }

    }

    private void createGameButtonClicked() {
        System.out.println("clicked create game...");
        final Dialog createGameDialog = new Dialog("Create game", skin);
        final TextField textFieldUsername = new TextField("challenger", skin);
        textFieldUsername.setSize(100, 40);
        textFieldUsername.setPosition(10, 80);
        createGameDialog.addActor(textFieldUsername);
        TextButton buttonCreateGameDialog = new TextButton("CreateGame", skin);
        buttonCreateGameDialog.setSize(100, 60);
        buttonCreateGameDialog.setPosition(10, 10);
        buttonCreateGameDialog.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                createGameButtonDialogClicked(textFieldUsername.getText());
                createGameDialog.hide();
            }
        });

        createGameDialog.addActor(buttonCreateGameDialog);
        stage.addActor(createGameDialog);

    }

    private void createGameButtonDialogClicked(String opponent) {
        ResponseMessage response = ClientConnection.getInstance().createNewGame(opponent, Games.KILL_ALL_ENEMY_UNITS);
        if(response.getType() == ResponseType.FAILURE) {
            labelInstruction.setText((String) response.getContent());
        } else {
            labelInstruction.setText("Successfully challenged " + opponent);
            Integer gameId = (Integer) response.getContent();
            System.out.println("New game has id: " + gameId);
        }
        System.out.println("create game button dialog clicked" + " challenged player: " + opponent);
    }

    public void gameSelected(int gameId) {
        ResponseMessage response = ClientConnection.getInstance().getGame(gameId);
        ResponseType type = response.getType();
        if(type.equals(ResponseType.SUCCESS)) {
            Game game = (Game) response.getContent();
            gsm.setState(new ActiveGameScreen(gsm, game));
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
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

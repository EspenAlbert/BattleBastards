package com.tdt4240.RawHeroes.topLayer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.mainMenuGamesHandler.clientGameState.ClientGameState;
import com.tdt4240.RawHeroes.network.client.ClientConnection;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseMessage;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseType;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Games;
import com.tdt4240.RawHeroes.view.uiElements.LabelFactory;
import com.tdt4240.RawHeroes.view.uiElements.MainMenuButtonsFactory;
import com.tdt4240.RawHeroes.view.uiElements.ScrollPaneFactory;
import com.tdt4240.RawHeroes.view.uiElements.ScrollTableFactory;
import com.tdt4240.RawHeroes.view.uiElements.TextFieldFactory;

import java.util.ArrayList;

/**
 * Created by Håvard on 20.04.2015.
 */
public class CreateGameScreen extends ScreenState {

    private final Stage stage;
    private final TextButton buttonAddFriend;
    private final Label labelInstruction;


    private Table scrollTable;
    private ScrollPane scrollPane;
    private ArrayList<Game> activeGames = new ArrayList<Game>();

    public CreateGameScreen(ScreenStateManager gsm) {
        super(gsm);
        System.out.println("Created game menu screen");
        stage = new Stage();



        int xPos = GameConstants.RESOLUTION_WIDTH/20;
        int scaleY = GameConstants.RESOLUTION_HEIGHT/5;
        int yPosLabelInstruction = GameConstants.RESOLUTION_HEIGHT*4/5;
        int yPosButtonCreateGame =  yPosLabelInstruction - scaleY;
        int yPosTextFieldGetGame = yPosButtonCreateGame - scaleY;

        buttonAddFriend = MainMenuButtonsFactory.createTableButton("Add Friend", GameConstants.RESOLUTION_WIDTH / 2, GameConstants.RESOLUTION_HEIGHT - 100, false);
        scrollTable = ScrollTableFactory.createScrollTable();
        scrollTable.add("Friend list");

        final TextField textFieldUsername = TextFieldFactory.createTextField("challenger", xPos + GameConstants.BUTTON_WIDTH/2, (int) stage.getHeight() / 2, false);
        stage.addActor(textFieldUsername);
        TextButton buttonCreateGame = MainMenuButtonsFactory.createButton("Create game",GameConstants.RESOLUTION_WIDTH/15 + GameConstants.BUTTON_WIDTH, GameConstants.RESOLUTION_HEIGHT/10);



        buttonAddFriend.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                addFriend(textFieldUsername.getText());
            }
        });
        buttonCreateGame.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                createGameButtonClicked(textFieldUsername.getText());
            }
        });

        labelInstruction = LabelFactory.createLabel("Create game", (int)buttonCreateGame.getX(), yPosLabelInstruction);
        final TextButton buttonBackToMainMenu = MainMenuButtonsFactory.createButton("Back", xPos, GameConstants.RESOLUTION_HEIGHT/10);
        buttonBackToMainMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                backToMainMenu();
            }
        });

        stage.addActor(buttonCreateGame);
        stage.addActor(buttonBackToMainMenu);

        scrollPane = ScrollPaneFactory.createScrollPane(scrollTable);

        Gdx.input.setInputProcessor(stage);
        stage.addActor(labelInstruction);
        stage.addActor(scrollPane);
        stage.addActor(buttonAddFriend);
    }

    private void createGameButtonClicked(String opponent) {
        ResponseMessage response = ClientConnection.getInstance().createNewGame(opponent, Games.KILL_ALL_ENEMY_UNITS);
        if(response.getType() == ResponseType.FAILURE) {
            labelInstruction.setText((String) response.getContent());
        } else {
            labelInstruction.setText("Successfully challenged " + opponent);
            Integer gameId = (Integer) response.getContent();
            System.out.println("New game has id: " + gameId);
            MainMenuScreen main = (MainMenuScreen) gsm.peek(0);
            main.addGameToTable(opponent, "Opponents turn", gameId);
        }
        System.out.println("create game button dialog clicked" + " challenged player: " + opponent);
        backToMainMenu();
    }

    private void backToMainMenu() {
        gsm.popOnly();
    }


    private void addFriend(String username) {
        ResponseMessage response = ClientConnection.getInstance().addToFriendList(username);
        if (response.getType() == ResponseType.FAILURE) {
            labelInstruction.setText((String) response.getContent());
        } else {
            labelInstruction.setText("Successfully added!");
            scrollTable.add(username);
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

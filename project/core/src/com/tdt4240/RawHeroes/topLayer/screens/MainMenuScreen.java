package com.tdt4240.RawHeroes.topLayer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tdt4240.RawHeroes.createGame.factory.GameBuilding;
import com.tdt4240.RawHeroes.independent.GameConstants;
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

    private Texture title;
    private Sprite sprite;

    private Table table, scrollTable;
    private ScrollPane scrollPane;

    public MainMenuScreen(ScreenStateManager gsm) {
        super(gsm);
        System.out.println("Created main menu screen");

        games = new ArrayList<ClientGameState>();
        //int[] myGames = ClientConnection.getInstance().getMyGames();
        int[] myGames = {1, 2 , 3, 4, 5, 6};
        //MainMenuView view = new MainMenuView(myGames);
        skin = new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
        stage = new Stage();

        int xPos = GameConstants.RESOLUTION_WIDTH/20;
        int scaleY = GameConstants.RESOLUTION_HEIGHT/5;
        int yPosLabelInstruction = GameConstants.RESOLUTION_HEIGHT*4/5;
        int yPosButtonCreateGame =  yPosLabelInstruction - scaleY;
        int yPosTextFieldGetGame = yPosButtonCreateGame - scaleY;
        int yPosButtonGetGame = yPosTextFieldGetGame - scaleY;

        buttonCreateGame = new TextButton("CreateGame", skin);
        buttonCreateGame.setPosition(xPos, yPosButtonCreateGame);
        buttonCreateGame.setSize(GameConstants.BUTTON_WIDTH, GameConstants.BUTTON_HEIGHT);
        labelInstruction = new Label("This is the main menu", skin);
        labelInstruction.setSize(GameConstants.LABEL_WIDTH,GameConstants.LABEL_HEIGHT);
        labelInstruction.setPosition(xPos, yPosLabelInstruction);

        textFieldGetGame = new TextField("29", skin);
        textFieldGetGame.setSize(GameConstants.TEXTFIELD_WIDTH, GameConstants.TEXTFIELD_HEIGHT);
        textFieldGetGame.setPosition(xPos, yPosTextFieldGetGame);
        textFieldGetGame.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());

        buttonGetGame = new TextButton("getGameById", skin);
        buttonGetGame.setPosition(xPos, yPosButtonGetGame);
        buttonGetGame.setSize(GameConstants.BUTTON_WIDTH, GameConstants.BUTTON_HEIGHT);

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

        title = new Texture(Gdx.files.internal("title.png"));
        sprite = new Sprite(title);

        sprite.setSize(300, 100);
        sprite.setPosition(0, GameConstants.RESOLUTION_HEIGHT - sprite.getHeight()*2);

        scrollTable = new Table(skin);
        table = new Table(skin);


        //putting stuff together
        scrollTable.add("SELECT GAME");
        for (int i = 0; i < myGames.length; i++){
            addGameToTable(Integer.toString(myGames[i]) , "gameStatus");
        }

        scrollPane = new ScrollPane(scrollTable);
        scrollPane.setBounds(GameConstants.RESOLUTION_WIDTH - 600, 0, 600, GameConstants.RESOLUTION_HEIGHT);



        Gdx.input.setInputProcessor(stage);
        stage.addActor(buttonCreateGame);
        stage.addActor(labelInstruction);
        stage.addActor(textFieldGetGame);
        stage.addActor(buttonGetGame);
        stage.addActor(scrollPane);



    }

    private void getGameButtonClicked() {
        int gameid = Integer.parseInt(textFieldGetGame.getText());
        gsm.setState(gsm.GAMESCREEN);
        /*
        ResponseMessage responseMessage = ClientConnection.getInstance().getGame(gameid);
        if(responseMessage.getType() == ResponseType.FAILURE) {
            labelInstruction.setText((CharSequence) responseMessage.getContent());
        } else {
            Game launcher = (Game) responseMessage.getContent();
            System.out.println("managed to get launcher: " + launcher.getId() + " with players: " + launcher.getPlayer1Nickname() + " player 2:" + launcher.getPlayer2Nickname());
            gsm.setState(new ActiveGameScreen(gsm, launcher));
        }
*/
    }

    private void createGameButtonClicked() {
        System.out.println("clicked create game...");
        final Dialog createGameDialog = new Dialog("Create game", skin);
        createGameDialog.setBounds(50,25, GameConstants.RESOLUTION_WIDTH - 100, GameConstants.RESOLUTION_HEIGHT- 50);
        final TextField textFieldUsername = new TextField("challenger", skin);
        textFieldUsername.setSize(GameConstants.TEXTFIELD_WIDTH, GameConstants.TEXTFIELD_HEIGHT);
        textFieldUsername.setPosition(createGameDialog.getWidth()/2 - textFieldUsername.getWidth()/2, createGameDialog.getHeight()/2);
        createGameDialog.addActor(textFieldUsername);
        TextButton buttonCreateGameDialog = new TextButton("CreateGame", skin);
        buttonCreateGameDialog.setSize(GameConstants.TEXTFIELD_WIDTH, GameConstants.TEXTFIELD_HEIGHT);
        buttonCreateGameDialog.setPosition(createGameDialog.getWidth()- 100 - buttonCreateGameDialog.getWidth(), 150);
        buttonCreateGameDialog.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                createGameButtonDialogClicked(textFieldUsername.getText());
                createGameDialog.hide();
            }
        });

        TextButton buttonBackToMainMenu = new TextButton("Back", skin);
        buttonBackToMainMenu.setSize(GameConstants.TEXTFIELD_WIDTH, GameConstants.TEXTFIELD_HEIGHT);
        buttonBackToMainMenu.setPosition(100, 150);
        buttonBackToMainMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                createGameDialog.remove();
            }
        });

        createGameDialog.addActor(buttonCreateGameDialog);
        createGameDialog.addActor(buttonBackToMainMenu);
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
        addGameToTable(opponent, "waiting for other player");
        System.out.println("create game button dialog clicked" + " challenged player: " + opponent);
    }

    public void addGameToTable(String opponent, String status){
        TextButton button = new TextButton(opponent + "           " + status, skin);
        button.setSize(GameConstants.BUTTON_WIDTH * 2, GameConstants.BUTTON_HEIGHT);
        scrollTable.row().height(button.getHeight() + 50);
        scrollTable.defaults().width(button.getWidth());
        scrollTable.add(button);
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
        spriteBatch.begin();
        sprite.draw(spriteBatch);
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

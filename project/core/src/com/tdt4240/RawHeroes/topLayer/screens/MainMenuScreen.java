package com.tdt4240.RawHeroes.topLayer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tdt4240.RawHeroes.gameLogic.inputListeners.GameClickedListener;
import com.tdt4240.RawHeroes.gameLogic.inputListeners.SurrenderGameClickListener;
import com.tdt4240.RawHeroes.independent.GameConstants;
import com.tdt4240.RawHeroes.mainMenuGamesHandler.clientGameState.ClientGameState;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseMessage;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseType;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;
import com.tdt4240.RawHeroes.network.client.ClientConnection;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Games;
import com.tdt4240.RawHeroes.view.uiElements.DialogFactory;
import com.tdt4240.RawHeroes.view.uiElements.LabelFactory;
import com.tdt4240.RawHeroes.view.uiElements.MainMenuButtonsFactory;
import com.tdt4240.RawHeroes.view.uiElements.ScrollPaneFactory;
import com.tdt4240.RawHeroes.view.uiElements.ScrollTableFactory;
import com.tdt4240.RawHeroes.view.uiElements.TextFieldFactory;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public class MainMenuScreen extends ScreenState {
    private final Stage stage;
    private final TextButton buttonCreateGame, buttonUpdateTable;
    private final Label labelInstruction;
    private final TextButton buttonSettingScreen;
    private ArrayList<ClientGameState> games;

    private Texture title;
    private Sprite sprite;

    private Table scrollTable;
    private ScrollPane scrollPane;
    private ArrayList<Game> activeGames = new ArrayList<Game>();

    public MainMenuScreen(ScreenStateManager gsm) {
        super(gsm);
        System.out.println("Created main menu screen");
        stage = new Stage();

        games = new ArrayList<ClientGameState>();


        int xPos = GameConstants.RESOLUTION_WIDTH/20;
        int scaleY = GameConstants.RESOLUTION_HEIGHT/5;
        int yPosLabelInstruction = GameConstants.RESOLUTION_HEIGHT*4/5;
        int yPosButtonCreateGame =  yPosLabelInstruction - scaleY;
        int yPosTextFieldGetGame = yPosButtonCreateGame - scaleY;
        int yPosButtonGetGame = yPosTextFieldGetGame - scaleY;

        buttonCreateGame = MainMenuButtonsFactory.createButton("CreateGame", xPos, yPosButtonCreateGame);
        buttonSettingScreen = MainMenuButtonsFactory.createButton("Settings" , xPos, yPosButtonGetGame);
        buttonUpdateTable = MainMenuButtonsFactory.createTableButton("Update games", GameConstants.RESOLUTION_WIDTH / 2, GameConstants.RESOLUTION_HEIGHT - 100, false);
        labelInstruction = LabelFactory.createLabel("This is the main menu", xPos, yPosLabelInstruction);
        scrollTable = ScrollTableFactory.createScrollTable();


        getAllGames();

        buttonCreateGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                createGameButtonClicked();
            }
        });


        buttonSettingScreen.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getSettingsButtonClicked();
            }
        });

        buttonUpdateTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getAllGames();
            }
        });

        title = new Texture(Gdx.files.internal("title.png"));

        scrollPane = ScrollPaneFactory.createScrollPane(scrollTable);

        Gdx.input.setInputProcessor(stage);
        stage.addActor(buttonCreateGame);
        stage.addActor(labelInstruction);
        stage.addActor(buttonSettingScreen);
        stage.addActor(scrollPane);
        stage.addActor(buttonUpdateTable);
    }

    private void getSettingsButtonClicked() {
        gsm.pushState(new SettingScreen(gsm));
    }

    public void getAllGames(){
        scrollTable.clear();
        activeGames.clear();
        ArrayList<Integer> myGames = (ArrayList<Integer>) ClientConnection.getInstance().getGameIds().getContent();
        for (Integer gameId : myGames) {
            ResponseMessage responseMessage = ClientConnection.getInstance().getGame(gameId);
            if(responseMessage.getType() == ResponseType.FAILURE){
                System.out.println("SOMETHING WRONG HAPPENED!");
            }
            else if(responseMessage.getType() == ResponseType.SUCCESS){
                Game game = (Game) responseMessage.getContent();
                System.out.println("managed to get game: " + game.getId() + " with players: " + game.getPlayer1Nickname() + " player 2:" + game.getPlayer2Nickname());
                activeGames.add(game);
            }
        }
        scrollTable.add("ACTIVE GAMES");
        for (int i = 0; i < activeGames.size(); i++){
            if(activeGames.get(i).getPlayer1Nickname().equals(clientConnection.getUsername())) {
                if (activeGames.get(i).getNextTurnIsPlayer1()) {
                    addGameToTable(activeGames.get(i).getPlayer2Nickname(), "Your turn", activeGames.get(i).getId());
                } else {
                    addGameToTable(activeGames.get(i).getPlayer2Nickname(), "Opponents turn", activeGames.get(i).getId());
                }
            }
            else{
                if (activeGames.get(i).getNextTurnIsPlayer1()) {
                    addGameToTable(activeGames.get(i).getPlayer1Nickname(), "Opponents turn", activeGames.get(i).getId());
                } else {
                    addGameToTable(activeGames.get(i).getPlayer1Nickname(), "Your turn", activeGames.get(i).getId());
                }
            }
        }
    }

    private void createGameButtonClicked() {
        System.out.println("clicked create game...");
        final Dialog createGameDialog = DialogFactory.createDialogFactory("Create game");

        final TextField textFieldUsername = TextFieldFactory.createTextField("challenger", (int)createGameDialog.getWidth()/2 -GameConstants.RESOLUTION_WIDTH/10, (int)createGameDialog.getHeight()/2, false);
        createGameDialog.addActor(textFieldUsername);
        TextButton buttonCreateGameDialog = MainMenuButtonsFactory.createButton("Create game",GameConstants.RESOLUTION_WIDTH*2/3 -GameConstants.RESOLUTION_WIDTH/10, GameConstants.RESOLUTION_HEIGHT/10);
        buttonCreateGameDialog.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                createGameButtonDialogClicked(textFieldUsername.getText());
                createGameDialog.hide();
            }
        });

        TextButton buttonBackToMainMenu = MainMenuButtonsFactory.createButton("Back", GameConstants.RESOLUTION_WIDTH/4 -GameConstants.RESOLUTION_WIDTH/10, GameConstants.RESOLUTION_HEIGHT/10);
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
    @Override
    public void setInputProcessor(){
        Gdx.input.setInputProcessor(stage);
    }

    public void setMsg(String msg){
        labelInstruction.setText(msg);
    }

    private void createGameButtonDialogClicked(String opponent) {
        ResponseMessage response = ClientConnection.getInstance().createNewGame(opponent, Games.KILL_ALL_ENEMY_UNITS);
        if(response.getType() == ResponseType.FAILURE) {
            labelInstruction.setText((String) response.getContent());
        } else {
            labelInstruction.setText("Successfully challenged " + opponent);
            Integer gameId = (Integer) response.getContent();
            System.out.println("New game has id: " + gameId);
            addGameToTable(opponent, "Opponents turn", gameId);
        }
        System.out.println("create game button dialog clicked" + " challenged player: " + opponent);
    }

    public void addGameToTable(String opponent, String status, int gameId){
        TextButton button = MainMenuButtonsFactory.createTableButton("Opponent: " + opponent + "           " + status, GameConstants.RESOLUTION_WIDTH/2 - GameConstants.RESOLUTION_WIDTH/10, GameConstants.RESOLUTION_HEIGHT, false);
        TextButton surrButton = MainMenuButtonsFactory.createTableButton("Surrender?", GameConstants.RESOLUTION_WIDTH / 2, GameConstants.RESOLUTION_HEIGHT, true);
        button.addListener( new GameClickedListener(this, gameId));
        surrButton.addListener(new SurrenderGameClickListener(this, gameId));
        scrollTable.row().height(button.getHeight() + GameConstants.RESOLUTION_HEIGHT / 10);
        scrollTable.add(button);
        scrollTable.add(surrButton);
    }

    public void gameSelected(int gameId) {
        ResponseMessage response = ClientConnection.getInstance().getGame(gameId);
        ResponseType type = response.getType();
        if(type.equals(ResponseType.SUCCESS)) {
            Game game = (Game) response.getContent();
            gsm.pushState(new ActiveGameScreen(gsm, game));
        }
    }
    public void deleteGame(int gameId){
        ResponseMessage response = ClientConnection.getInstance().deleteGame(gameId);
        ResponseType type = response.getType();
        if(type.equals(ResponseType.SUCCESS)){
            labelInstruction.setText("You have surrendred the game! " + gameId);
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

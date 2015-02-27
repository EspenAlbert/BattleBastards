package com.tdt4240.RawHeroes.game.screens;

import com.tdt4240.RawHeroes.game.gameState.GameState;
import com.tdt4240.RawHeroes.network.client.ClientConnection;
import com.tdt4240.RawHeroes.network.message.Message;
import com.tdt4240.RawHeroes.network.message.MessageType;
import com.tdt4240.RawHeroes.render.view.MainMenuView;

/**
 * Created by espen1 on 27.02.2015.
 */
public class MainMenuScreen extends ScreenState {
    public MainMenuScreen(ScreenStateManager gsm) {
        super(gsm);
        int[] myGames = ClientConnection.getInstance().getMyGames();
        MainMenuView view = new MainMenuView(myGames);

    }

    public void gameSelected(int gameId) {
        Message response = ClientConnection.getInstance().getGame(gameId);
        MessageType type = response.getType();
        if(type.equals(MessageType.SUCCESS)) {
            GameState gameState = (GameState) response.getContent();
            gsm.setState(new ActiveGameScreen(gsm, gameState));
        }
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {

    }

    @Override
    public void dispose() {

    }
}

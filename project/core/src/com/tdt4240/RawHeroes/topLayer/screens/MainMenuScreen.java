package com.tdt4240.RawHeroes.topLayer.screens;

import com.tdt4240.RawHeroes.network.communication.Response.ResponseMessage;
import com.tdt4240.RawHeroes.network.communication.Response.ResponseType;
import com.tdt4240.RawHeroes.topLayer.commonObjects.Game;
import com.tdt4240.RawHeroes.network.client.ClientConnection;
import com.tdt4240.RawHeroes.view.topLayer.MainMenuView;

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

    }

    @Override
    public void dispose() {

    }
}

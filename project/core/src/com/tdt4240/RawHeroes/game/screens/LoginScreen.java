package com.tdt4240.RawHeroes.game.screens;

import com.tdt4240.RawHeroes.network.client.ClientConnection;
import com.tdt4240.RawHeroes.network.message.Message;
import com.tdt4240.RawHeroes.network.message.MessageType;

/**
 * Created by espen1 on 27.02.2015.
 */
public class LoginScreen extends ScreenState {
    protected LoginScreen(ScreenStateManager gsm) {
        super(gsm);
        //Create textfield for login and password
        // Create login button with listener


    }
    public void updateButtonClicked() {
        String username = null, pwd = null;
        //Username = usernameTextfield.getText();
        //Pwd = pwdTextfield.getText();
        Message response = ClientConnection.getInstance().login(username, pwd);
        MessageType type = response.getType();
        if(type.equals(MessageType.SUCCESS)) {
            ClientConnection.getInstance().setUsername(username);
            ClientConnection.getInstance().setPassword(pwd);
            gsm.setState(ScreenStateManager.MAINMENU);
        }
        else {
            String errorMessage = (String) response.getContent();
            System.out.println("Failed to login, errormessage: " + errorMessage);
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

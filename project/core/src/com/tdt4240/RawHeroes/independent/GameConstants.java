package com.tdt4240.RawHeroes.independent;

import com.badlogic.gdx.Gdx;

/**
 * Created by espen1 on 27.02.2015.
 */
public class GameConstants {

    private static final boolean useAndroid = false;
    public static final int RESOLUTION_WIDTH = useAndroid ? Gdx.graphics.getWidth() : 960;
    public static final int RESOLUTION_HEIGHT = useAndroid ? Gdx.graphics.getHeight() : 540;
    public static final int BUTTON_WIDTH = Math.round(GameConstants.RESOLUTION_WIDTH  / 5);
    public static final int BUTTON_HEIGHT = Math.round(GameConstants.RESOLUTION_HEIGHT  / 9);
    public static final int TEXTFIELD_WIDTH = Math.round(GameConstants.RESOLUTION_WIDTH  / 4);
    public static final int TEXTFIELD_HEIGHT = Math.round(GameConstants.RESOLUTION_HEIGHT / 8);
    public static final int LABEL_WIDTH = Math.round(GameConstants.RESOLUTION_HEIGHT / 9);
    public static final int LABEL_HEIGHT = Math.round(GameConstants.RESOLUTION_HEIGHT / 8);
    public static final int CELL_WIDTH = Math.round(GameConstants.RESOLUTION_WIDTH / 6);
    public static final int CELL_HEIGHT = CELL_WIDTH;
    public static final int SCALE_HEIGHT = GameConstants.RESOLUTION_HEIGHT/5;
    public static final int SPACE_BETWEEN = 0;
    public static final int MAX_ENERGY = 100;
    public static final int SLIDER_WIDTH = Math.round(GameConstants.RESOLUTION_WIDTH  / 4);
    public static final int SLIDER_HEIGHT = Math.round(GameConstants.RESOLUTION_HEIGHT  / 9);

    public static final float GAME_WIDTH = 7; //Says how many cells visible at the same time
    public static final float GAME_HEIGHT = 5;
    public static final float EXTRA_SPACE_BUTTONS = 1;
    public static final int SERVER_PORT =3310;
    public static float CAMERA_SPEED = 0.07f;

    public static void setCameraSpeed(float newCAMERA_SPEED){
        CAMERA_SPEED = 0.07f * newCAMERA_SPEED;
        System.out.println(CAMERA_SPEED);
    }

    public static String SERVER_IP = "129.241.134.184";
   // public static String SERVER_IP = "localhost";
}

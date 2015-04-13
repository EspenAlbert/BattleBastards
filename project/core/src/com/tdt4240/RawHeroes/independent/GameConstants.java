package com.tdt4240.RawHeroes.independent;

import com.badlogic.gdx.Gdx;

/**
 * Created by espen1 on 27.02.2015.
 */
public class GameConstants {

    private static final boolean useAndroid = false;
    public static final int RESOLUTION_WIDTH = useAndroid ? Gdx.graphics.getWidth() : 800;
    public static final int RESOLUTION_HEIGHT = useAndroid ? Gdx.graphics.getHeight() : 800;
    public static final int BUTTON_WIDTH = Math.round(GameConstants.RESOLUTION_WIDTH / 5);
    public static final int BUTTON_HEIGHT = Math.round(GameConstants.RESOLUTION_HEIGHT / 9);
    public static final int SPACE_BETWEEN = 0;
    public static final int SUPER_VAR = 0;

}

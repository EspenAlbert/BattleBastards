package com.tdt4240.RawHeroes.render.lowLayer;

import com.badlogic.gdx.Gdx;

/**
 * Created by espen1 on 27.02.2015.
 */
public class Constants {

    private static final boolean useAndroid = false;
    public static final int RESOLUTION_WIDTH = useAndroid ? Gdx.graphics.getWidth() : 400;
    public static final int RESOLUTION_HEIGHT = useAndroid ? Gdx.graphics.getHeight() : 800;

}

package com.tdt4240.RawHeroes.view.uiElements;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.graphics.g2d.BitmapFont;
        import com.badlogic.gdx.graphics.g2d.TextureAtlas;
        import com.badlogic.gdx.scenes.scene2d.ui.Skin;
        import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
        import com.tdt4240.RawHeroes.independent.GameConstants;

/**
 * Created by Håvard on 19.04.2015.
 */
public class MainMenuButtonsFactory{

    private static Skin skin = new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas(Gdx.files.internal("uiskin.atlas")));

    public static TextButton createButton(String text, int xPos, int yPos) {

        return createButton(text, xPos, yPos, GameConstants.BUTTON_WIDTH, GameConstants.BUTTON_HEIGHT);
    }

    public static TextButton createTableButton(String text, int xPos, int yPos, boolean surr) {
        if(surr) {
            return createButton(text, xPos, yPos, GameConstants.BUTTON_WIDTH / 2, GameConstants.BUTTON_HEIGHT);
        }
        else{
                return createButton(text, xPos, yPos, GameConstants.BUTTON_WIDTH * 2, GameConstants.BUTTON_HEIGHT);
            }
    }

    public static TextButton createButton(String text, int xPos, int yPos, int xSize, int ySize) {

        TextButton button = new TextButton(text, skin);
        button.setPosition(xPos, yPos);
        button.setSize(xSize, ySize);

        return button;
    }
}

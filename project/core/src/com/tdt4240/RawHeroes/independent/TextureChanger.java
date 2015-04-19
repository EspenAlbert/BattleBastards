package com.tdt4240.RawHeroes.independent;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;


/**
 * Created by Endre on 19.04.2015.
 */
public class TextureChanger {

    public static Texture changeColor(Texture texture, Color playerColor){
        Pixmap pixelMap = texture.getTextureData().consumePixmap();
        Color removeColor = new Color(1, 0, 1, 1);
        pixelMap.setColor(playerColor);
        for (int y = 0; y < pixelMap.getHeight(); y++){
            for(int x = 0; x <pixelMap.getWidth(); x++){
                Color pixelColor = new Color();
                Color.rgba8888ToColor(pixelColor, pixelMap.getPixel(x, y));
                if (pixelColor == removeColor){
                    pixelMap.drawPixel(x, y, playerColor.toIntBits());
                }
            }
        }
        texture = new Texture(pixelMap);
        return texture;
    }
}

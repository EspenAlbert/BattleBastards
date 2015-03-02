package com.tdt4240.RawHeroes.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tdt4240.RawHeroes.topLayer.launcher.RawHeroesOfBegredeligeStudenter;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new RawHeroesOfBegredeligeStudenter(), config);
	}
}

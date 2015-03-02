package com.tdt4240.RawHeroes.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.tdt4240.RawHeroes.topLayer.launcher.RawHeroesOfBegredeligeStudenter;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new RawHeroesOfBegredeligeStudenter(), config);
	}
}

package com.tdt4240.RawHeroes.android;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.tdt4240.RawHeroes.topLayer.launcher.BattleBastards;

public class AndroidLauncher extends AndroidApplication {
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        //StrictMode.setThreadPolicy(new StrictMode.StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        initialize(new BattleBastards(), config);
	}
}

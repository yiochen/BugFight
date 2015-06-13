package yiou.chen.bugfight.android;

import android.os.Bundle;
import android.os.Handler;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import yiou.chen.bugfight.BugFightGame;

public class AndroidLauncher extends AndroidApplication {
    public static Handler uiThread;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        uiThread=new Handler();
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useAccelerometer=false;
        config.useCompass=false;
		initialize(new BugFightGame(new AndroidFirebase(this)), config);
	}
}

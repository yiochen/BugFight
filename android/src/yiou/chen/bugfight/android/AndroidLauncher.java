package yiou.chen.bugfight.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import yiou.chen.bugfight.BugFightGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useAccelerometer=false;
        config.useCompass=false;
		initialize(new BugFightGame(new AndroidBluetooth(this.getApplication())), config);
	}
}

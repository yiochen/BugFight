package yiou.chen.bugfight.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import yiou.chen.bugfight.BugFightGame;
import yiou.chen.bugfight.Constants;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = Constants.GAME_NAME;
        config.width = (int)Constants.GAME_WIDTH;
        config.height =(int)Constants.GAME_HEIGHT;
        new LwjglApplication(new BugFightGame(new DummyBluetooth()), config);
    }
}

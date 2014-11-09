package yiou.chen.bugfight;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Yiou on 11/8/2014.
 */
public class Constants {
    public static String GAME_NAME="bug fight";
    public static float GAME_WIDTH=720;
    public static float GAME_HEIGHT=1280;
    public static Rectangle WorldBound=new Rectangle(0,0,GAME_WIDTH,GAME_HEIGHT);
    public static float LIFE=200;

    public static float PANEL_HEIGHT=200;
    public static float POWER_BAR_HEIGHT=10;
    public static float LIFE_BAR_LENGTH=300;
    public static float LIFE_BAR_Y=1260;
    public static float SPOWN_EDGE=70;
    public static int HAND_POWER=1;
    public static float endLine=PANEL_HEIGHT+POWER_BAR_HEIGHT;

}

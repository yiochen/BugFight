package yiou.chen.bugfight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Yiou on 11/8/2014.
 */
public class Assets {
    public static Texture background;
    public static Texture normal;
    public static Texture beetle;
    public static Texture locust;
    public static Texture ladybug;
    public static Texture power;
    public static Texture bugPanel;
    public static BitmapFont font;
    public static Sound slap;
    public static Sound pang;
    public static Sound pain;
    public static Texture normal_p;
    public static Texture beetle_p;
    public static Texture locust_p;
    public static Texture ladybug_p;
    public static Texture hpPanel;
    public static Texture hpBar;
    public static Texture hpIndicator;
    public static Texture logo;
    public static Texture grass;
    public static Texture splash;
    public static Texture yellow;
    public static Texture pink;
    public static Texture gray;
    public static Texture darkGray;
    public static Texture blueDrop;
    public static Texture pinkDrop;
    public static Texture yellowDrop;
    public static Vector2 sNormal;
    public static Vector2 sBeetle;
    public static Vector2 sLocust;
    public static Vector2 sLady;
    public static Rectangle rHPIndicator;
    public static Rectangle rHPBar;
    public static Rectangle rHPPanel;
    public static Rectangle rBugPanel;
    public static Rectangle rSplash;
    public static Rectangle rPowerBackground;
    public static Texture powerBackground;
    public static Rectangle rPower;
    public static Rectangle rGrass;
    public static Rectangle rLogo;
    public static Vector2 sDrop;
    public static Rectangle rLocust;
    public static Rectangle rNormal;
    public static Rectangle rBeetle;
    public static Rectangle rLady;

    public static Texture loadTexture(String file){
        return new Texture(Gdx.files.internal(file));
    }

    /**
     * Spawned things have pivots in the center. (bugs, drops)
     * Staic things have pivots in the top left corner (background, panel)
     */
    public static void load(){
        //image
        normal =loadTexture("data/Normal.png");
        sNormal=new Vector2(93,105);
        beetle =loadTexture("data/Beetle.png");
        sBeetle=new Vector2(155,144);
        locust =loadTexture("data/Locust.png");
        sLocust=new Vector2(111,140);
        ladybug=loadTexture("data/Ladybug.png");
        sLady=new Vector2(79,69);
        ladybug_p=loadTexture("data/Ladybug_p.png");
        rLady=new Rectangle(452,1182,70,61);
        beetle_p=loadTexture("data/Beetle_p.png");
        rBeetle=new Rectangle(200,1176,75,72);
        normal_p=loadTexture("data/Normal_p.png");
        rNormal=new Rectangle(79,1176,66,77);
        locust_p=loadTexture("data/Locust_p.png");
        rLocust=new Rectangle(333,1176,55,72);

        blueDrop=loadTexture("data/Blue_drop.png");
        pinkDrop=loadTexture("data/Pink_drop.png");
        yellowDrop=loadTexture("data/Yellow_drop.png");
        sDrop=new Vector2(54,71);

        yellow=loadTexture("data/Yellow.png");
        pink=loadTexture("data/Pink.png");
        gray=loadTexture("data/Gray.png");
        darkGray=loadTexture("data/Dark_gray.png");

        logo=loadTexture("data/bugMeLogo.png");
        rLogo=new Rectangle(65,342,574,251);
        background=loadTexture("data/Background.png");
        grass=loadTexture("data/Grass.png");
        rGrass=new Rectangle(0,828,720,452);
        power =loadTexture("data/Power.png");
        rPower=new Rectangle(51,1274,614,6);
        powerBackground=loadTexture("data/PowerBackground.png");
        rPowerBackground=new Rectangle(46,1271,627,9);


        splash=loadTexture("data/splash.png");
        rSplash=new Rectangle(66,270,499,499);
        bugPanel =loadTexture("data/Downbox.png");
        rBugPanel=new Rectangle(0,1083,720,197);
        hpPanel=loadTexture("data/Upbox.png");
        rHPPanel=new Rectangle(180,0,361,133);
        hpBar=loadTexture("data/HP.png");
        rHPBar=new Rectangle(238,19,228,32);
        hpIndicator=loadTexture("data/HP_indicator.png");
        rHPIndicator=new Rectangle(325,3,49,59);




        font = new BitmapFont(Gdx.files.internal("font/Mathlete.fnt"), Gdx.files.internal("font/Mathlete.png"), false);


       //audio
        slap=Gdx.audio.newSound(Gdx.files.internal("data/slap.mp3"));
        pang=Gdx.audio.newSound(Gdx.files.internal("data/pang.mp3"));
        pain=Gdx.audio.newSound(Gdx.files.internal("data/pain.mp3"));
    }

}

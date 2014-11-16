package yiou.chen.bugfight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by Yiou on 11/8/2014.
 */
public class Assets {
    public static Texture background;
    public static Texture bug1;
    public static Texture bug2;
    public static Texture bug3;
    public static Texture bug4;
    public static Texture bug5;
    public static Texture explosion;
    public static Texture progress;
    public static Texture panel;
    public static BitmapFont font;
    public static Sound slap;
    public static Sound pang;
    public static Sound pain;

    public static Texture loadTexture(String file){
        return new Texture(Gdx.files.internal(file));
    }

    public static void load(){
        background=loadTexture("data/background.jpg");
        bug1=loadTexture("data/bug_1.png");
        bug2=loadTexture("data/bug_2.png");
        bug3=loadTexture("data/bug_3.png");
        bug4=loadTexture("data/bug_4.png");
        bug5=loadTexture("data/bug_5.png");
        explosion=loadTexture("data/explosion.png");
        progress=loadTexture("data/progressBar.png");
        panel=loadTexture("data/panel.png");
        font = new BitmapFont(Gdx.files.internal("data/font.fnt"), Gdx.files.internal("data/font.png"), false);
        slap=Gdx.audio.newSound(Gdx.files.internal("data/slap.mp3"));
        pang=Gdx.audio.newSound(Gdx.files.internal("data/pang.mp3"));
        pain=Gdx.audio.newSound(Gdx.files.internal("data/pain.mp3"));
    }

}

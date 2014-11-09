package yiou.chen.bugfight.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Iterator;

import yiou.chen.bugfight.Assets;
import yiou.chen.bugfight.BugFightGame;
import yiou.chen.bugfight.Constants;
import yiou.chen.bugfight.interfaces.BlueToothCallback;
import yiou.chen.bugfight.interfaces.Renderable;
import yiou.chen.bugfight.interfaces.Updateable;
import yiou.chen.bugfight.object.Bug;

/**
 * Created by Yiou on 11/9/2014.
 */
public class MainScreen extends ScreenAdapter implements Renderable{
    private final BugFightGame game;
    private final BlueToothCallback bluetooth;
    private final OrthographicCamera camera;
    private final BitmapFont font;
    private SpriteBatch batch;
    private Rectangle optionBound1;
    private Rectangle optionBound2;

    public MainScreen(BugFightGame game){
        this.game=game;
        this.bluetooth=game.blCallback;
        camera=new OrthographicCamera();
        camera.setToOrtho(false, Constants.GAME_WIDTH,Constants.GAME_HEIGHT);
        this.font=Assets.font;
        Gdx.input.setInputProcessor(new InputListener());
    }

    @Override
    public void render(float delta) {
        this.batch=game.batch;
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        draw(batch);
        batch.end();

    }

    @Override
    public void draw(Batch batch) {

        drawBackground();
        drawTitle();
        drawOptions();
    }

    private void drawOptions() {
        font.setColor(Color.WHITE);
        font.setScale(1, 1);
        batch.draw(Assets.panel, 0, Constants.GAME_HEIGHT / 2 - 110, 300, 20);
        optionBound1=new Rectangle(0, Constants.GAME_HEIGHT / 2 - 110, 300, 20);
        batch.draw(Assets.panel,0,Constants.GAME_HEIGHT/2-140,300,20);
        optionBound2=new Rectangle(0,Constants.GAME_HEIGHT/2-140,300,20);
        font.draw(batch,"Open as Server",0,Constants.GAME_HEIGHT/2-90);
        font.draw(batch,"Connect to",0,Constants.GAME_HEIGHT/2-120);

    }

    private void drawTitle() {
        font.setColor(Color.WHITE);
        font.setScale(3,3);
        font.draw(batch,Constants.GAME_NAME,0,Constants.GAME_HEIGHT/2);
    }

    private void drawBackground() {
        batch.draw(Assets.background, 0, 0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
    }
    private class InputListener extends InputAdapter {
        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            float y = screenY;
            float x = screenX;
            y = Constants.GAME_HEIGHT - y / Gdx.graphics.getHeight() * Constants.GAME_HEIGHT;
            x = x / Gdx.graphics.getWidth() * Constants.GAME_WIDTH;
            if (optionBound1.contains(x,y)){
                //open as server;
                game.setScreen(new ServerScreen(game));
            }
            if (optionBound2.contains(x,y)){
                //connect as client
                game.setScreen(new ClientScreen(game));
            }
            return true;
        }
    }
}

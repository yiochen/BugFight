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

import java.util.ArrayList;
import java.util.List;

import yiou.chen.bugfight.Assets;
import yiou.chen.bugfight.BugFightGame;
import yiou.chen.bugfight.Constants;
import yiou.chen.bugfight.interfaces.BlueToothCallback;
import yiou.chen.bugfight.interfaces.Renderable;

/**
 * Created by Yiou on 11/9/2014.
 */
public class ClientScreen extends ScreenAdapter implements Renderable{
    private final BugFightGame game;
    private final BlueToothCallback bluetooth;
    private final OrthographicCamera camera;
    private final BitmapFont font;
    private List<Rectangle> bounds;
    private List<String> names;
    private SpriteBatch batch;
    public ClientScreen(BugFightGame game){
        this.game=game;
        this.bluetooth=game.blCallback;
        camera=new OrthographicCamera();
        camera.setToOrtho(false, Constants.GAME_WIDTH,Constants.GAME_HEIGHT);
        this.font= Assets.font;
        Gdx.input.setInputProcessor(new InputListener());
        this.bounds=new ArrayList<Rectangle>();
        this.names=new ArrayList<String>();
        if (bluetooth!=null){
            names=bluetooth.getPaiedList();
        }
    }

    @Override
    public void render(float delta) {
        if (bluetooth!=null && bluetooth.isConnected()){
            game.setScreen(new GameScreen(game));
        }
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
        drawList();
    }

    private void drawList() {
        font.setColor(Color.WHITE);
        font.setScale(1, 1);
       for (int i=0; i<names.size();i++){

           batch.draw(Assets.panel, 0, Constants.GAME_HEIGHT / 2 -i*40, 300, 20);
           Rectangle bound=new Rectangle(0, Constants.GAME_HEIGHT / 2-i*40, 300, 20);
           bounds.add(bound);
           font.draw(batch,names.get(i),0,Constants.GAME_HEIGHT/2+20-i*40);

       }
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
            for (int i=0;i<bounds.size();i++){
                if  (bounds.get(i).contains(x,y)){
                    bluetooth.chooseDevice(names.get(i));
                    bluetooth.connectAsClient();
                    break;
                }
            }
            return true;
        }
    }
}

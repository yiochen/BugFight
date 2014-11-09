package yiou.chen.bugfight.screen;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import yiou.chen.bugfight.Assets;
import yiou.chen.bugfight.BugFightGame;
import yiou.chen.bugfight.Constants;
import yiou.chen.bugfight.interfaces.BlueToothCallback;
import yiou.chen.bugfight.interfaces.Renderable;

/**
 * Created by Yiou on 11/9/2014.
 */
public class ServerScreen extends ScreenAdapter implements Renderable{
    private final BugFightGame game;
    private final BlueToothCallback bluetooth;
    private final OrthographicCamera camera;
    private final BitmapFont font;
    private SpriteBatch batch;
    public ServerScreen(BugFightGame game){
        this.game=game;
        this.bluetooth=game.blCallback;

        if (bluetooth!=null){
            bluetooth.turnOn();
            bluetooth.openServer();
        }
        camera=new OrthographicCamera();
        camera.setToOrtho(false, Constants.GAME_WIDTH,Constants.GAME_HEIGHT);
        this.font= Assets.font;
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
        drawMessage();
    }
    private void drawBackground() {
        batch.draw(Assets.background, 0, 0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
    }
    private void drawMessage() {
        font.setColor(Color.WHITE);
        font.setScale(2,2);
        font.draw(batch,"Waiting for opponent",0,Constants.GAME_HEIGHT/2);
    }
}

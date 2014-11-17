package yiou.chen.bugfight.screen;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import yiou.chen.bugfight.Assets;
import yiou.chen.bugfight.BugFightGame;
import yiou.chen.bugfight.Constants;
import yiou.chen.bugfight.interfaces.BluetoothCallback;
import yiou.chen.bugfight.interfaces.Renderable;

/**
 * Created by Yiou on 11/9/2014.
 */
public class NormalScreen extends ScreenAdapter implements Renderable {
    protected final BugFightGame game;
    protected final BluetoothCallback bluetooth;
    private final OrthographicCamera camera;
    private final BitmapFont font;
    private SpriteBatch batch;
    public NormalScreen(BugFightGame game){
        this.game=game;
        this.bluetooth=game.blCallback;
        initializeBluetooth();
        camera=new OrthographicCamera();
        camera.setToOrtho(false, Constants.GAME_WIDTH,Constants.GAME_HEIGHT);
        this.font= Assets.font;
    }

    private void initializeBluetooth() {
        if (bluetooth!=null){
            bluetooth.turnOn();

        }
    }

    @Override
    public void draw(Batch batch) {

    }

    @Override
    public void render(float delta) {
        updateBluetooth();
        super.render(delta);
    }

    private void updateBluetooth() {

    }
}

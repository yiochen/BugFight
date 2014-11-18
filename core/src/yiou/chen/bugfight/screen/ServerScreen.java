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
import yiou.chen.bugfight.interfaces.BluetoothCallback;
import yiou.chen.bugfight.interfaces.Renderable;

/**
 * Created by Yiou on 11/9/2014.
 */
public class ServerScreen extends AbstractScreen {

    private boolean clientConnected = false;

    public ServerScreen(BugFightGame game) {
        super(game);
        if (this.bluetooth != null) {
            if (bluetooth.isBluetoothOn()){
                bluetooth.openServer();
                status= Constants.STATUS.RUNNING;
            }else {
                bluetooth.turnOn();
                status= Constants.STATUS.PREPARE;
            }
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (status == Constants.STATUS.PREPARE && bluetooth != null && bluetooth.isBluetoothOn()) {
            bluetooth.openServer();
        }
        if (status == Constants.STATUS.RUNNING&&bluetooth.isConnected()) {
            if (bluetooth.read() == 10) {
                clientConnected = true;
                game.setScreen(new GameScreen(game));
            }
        }
        batch.begin();
        draw(batch);
        batch.end();
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        drawMessage();
    }
    @Override
    protected void drawBackground() {
        super.drawBackground();
        drawBatch(Assets.grass,Assets.rGrass);
    }
    private void drawMessage() {
        font.setColor(Color.WHITE);
        //font.setScale(2,2);
        font.draw(batch, "Waiting for opponent", 0, Constants.GAME_HEIGHT / 2);
    }

    @Override
    public boolean onTouch(float x, float y) {
        return true;
    }
}

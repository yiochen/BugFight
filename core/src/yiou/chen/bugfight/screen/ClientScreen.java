package yiou.chen.bugfight.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

import yiou.chen.bugfight.Assets;
import yiou.chen.bugfight.BugFightGame;
import yiou.chen.bugfight.Constants;

/**
 * Created by Yiou on 11/9/2014.
 */
public class ClientScreen extends AbstractScreen {

    private List<Rectangle> bounds;
    private List<String> names;

    public ClientScreen(BugFightGame game) {
        super(game);
        if (bluetooth != null) {
            bluetooth.turnOn();
        }

        this.bounds = new ArrayList<Rectangle>();
        this.names = new ArrayList<String>();

    }

    @Override
    protected void drawBackground() {
        super.drawBackground();
        drawBatch(Assets.grass, Assets.rGrass);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (status == Constants.STATUS.PREPARE && bluetooth != null && bluetooth.isBluetoothOn()) {

            names = bluetooth.getPaiedList();

            status = Constants.STATUS.RUNNING;
        }
        if (status == Constants.STATUS.RUNNING && bluetooth.isConnected()) {
            bluetooth.write(10);
            game.setScreen(new GameScreen(game));
        }
        batch.begin();
        draw(batch);
        batch.end();
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        drawList();
    }

    private void drawList() {
        font.setColor(Color.WHITE);
        font.setScale(1, 1);
        for (int i = 0; i < names.size(); i++) {

            batch.draw(Assets.bugPanel, 0, Constants.GAME_HEIGHT / 2 - i * 40, 300, 20);
            Rectangle bound = new Rectangle(0, Constants.GAME_HEIGHT / 2 - i * 40, 300, 20);
            bounds.add(bound);
            font.draw(batch, names.get(i), 0, Constants.GAME_HEIGHT / 2 + 20 - i * 40);

        }
    }

    @Override
    public boolean onTouch(float x, float y) {
        for (int i = 0; i < bounds.size(); i++) {
            if (bounds.get(i).contains(x, y)) {
                if (bluetooth != null) {
                    bluetooth.chooseDevice(names.get(i));
                    bluetooth.connectAsClient();
                    break;
                }
            }
        }
        return true;
    }
}

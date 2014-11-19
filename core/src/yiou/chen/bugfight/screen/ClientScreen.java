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
        if (!bluetooth.isBluetoothOn()) {
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
            status=Constants.STATUS.RUNNING;
        }
        if (status==Constants.STATUS.RUNNING && bluetooth !=null&& bluetooth.isBluetoothOn()){
            names = bluetooth.getPaiedList();
        }
        if (status == Constants.STATUS.BL_OK && bluetooth != null && bluetooth.isBluetoothOn() && bluetooth.isConnected()) {
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
            Rectangle bound = drawLeftText(names.get(i),1200-i*104,10,Constants.TEXT_M);
            bounds.add(bound);
        }
    }

    @Override
    public boolean onTouch(float x, float y) {
        bluetooth.write(10);
        if (status== Constants.STATUS.RUNNING ||status==Constants.STATUS.BL_OK) {
            for (int i = 0; i < bounds.size(); i++) {
                if (bounds.get(i).contains(x, y)) {
                    if (bluetooth.isBluetoothOn()) {
                        bluetooth.chooseDevice(names.get(i));
                        bluetooth.connectAsClient();
                        status = Constants.STATUS.BL_OK;
                        break;
                    }else{
                        bluetooth.turnOn();
                        status=Constants.STATUS.RUNNING;
                    }
                }
            }
        }
        return true;
    }
}

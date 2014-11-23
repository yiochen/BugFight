package yiou.chen.bugfight.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

import yiou.chen.bugfight.Assets;
import yiou.chen.bugfight.BugFightGame;
import yiou.chen.bugfight.Constants;

/**
 * Created by Yiou on 11/22/2014.
 */
public class MainScreen extends AbstractScreen{
    private Rectangle optionBound1;
    private Rectangle optionBound2;
    private Object lock=new Object();

    public MainScreen(BugFightGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        draw(batch);
        batch.end();
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        drawTitle();
        drawOptions();

    }

    @Override
    protected void drawBackground() {
        super.drawBackground();
        drawBatch(Assets.grass,Assets.rGrass);
    }

    private void drawOptions() {
        font.setColor(Color.WHITE);
        Rectangle op1=drawCenterText("Single Player Mode",562, Constants.TEXT_L);
        optionBound1=op1 ;
        Rectangle op2=drawCenterText("Play with Friend",422,Constants.TEXT_L);
        optionBound2=op2;
    }

    private void drawTitle() {
        drawBatch(Assets.splash,Assets.rSplash);
        drawBatch(Assets.logo, Assets.rLogo);
    }


    @Override
    public boolean onTouch(float x, float y) {
        if (optionBound1.contains(x,y)){
           //play offline mode;
           //game.setScreen(new ServerScreen(game));
        }
        if (optionBound2.contains(x,y)){
            //play bluetooth mode
            // TODO if bluetooth is not open. turn on bluetooth.
            if (bluetooth==null){
                //TODO tell user that the bluetooth is not available.
                return true;
            }else if  (!bluetooth.isBluetoothOn()){
                bluetooth.turnOn();
                while (!bluetooth.isBluetoothOn()){}
                game.setScreen(new LoginScreen(game));
                this.dispose();
            }
        }
        return true;
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}

package yiou.chen.bugfight.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

import yiou.chen.bugfight.Assets;
import yiou.chen.bugfight.BugFightGame;
import yiou.chen.bugfight.Constants;

/**
 * Created by Yiou on 11/9/2014.
 */
public class MainScreen extends AbstractScreen{


    private Rectangle optionBound1;
    private Rectangle optionBound2;

    public MainScreen(BugFightGame game){
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
        font.setScale(Constants.TEXT_L);

        batch.draw(Assets.bugPanel, 0, Constants.GAME_HEIGHT / 2 - 110, 300, 20);
        optionBound1=new Rectangle(0, Constants.GAME_HEIGHT / 2 - 110, 300, 20);
        batch.draw(Assets.bugPanel,0,Constants.GAME_HEIGHT/2-140,300,20);
        optionBound2=new Rectangle(0,Constants.GAME_HEIGHT/2-140,300,20);
        font.draw(batch,"Open as Server",0,Constants.GAME_HEIGHT/2-90);
        font.setScale(Constants.TEXT_M);
        font.draw(batch,"Connect to",0,Constants.GAME_HEIGHT/2-120);

    }

    private void drawTitle() {
        drawBatch(Assets.splash,Assets.rSplash);
        drawBatch(Assets.logo, Assets.rLogo);
    }


    @Override
    public boolean onTouch(float x, float y) {
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

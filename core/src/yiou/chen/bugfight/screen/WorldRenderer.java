package yiou.chen.bugfight.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.Iterator;

import yiou.chen.bugfight.Assets;
import yiou.chen.bugfight.Constants;
import yiou.chen.bugfight.World;
import yiou.chen.bugfight.object.Bug;

/**
 * Created by Yiou on 11/8/2014.
 */
public class WorldRenderer {
    private final SpriteBatch batch;
    private final World world;
    private final OrthographicCamera camera;





    public WorldRenderer(SpriteBatch batch, World world) {
        this.world = world;
        this.batch = batch;
        camera=new OrthographicCamera();
        camera.setToOrtho(false,Constants.GAME_WIDTH,Constants.GAME_HEIGHT);

    }




    public void render() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        renderBackground();
        renderGameObjects();
        renderController();
    }

    public void renderBackground() {
        batch.disableBlending();
        batch.begin();
        batch.draw(Assets.background, 0, 0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        batch.end();
    }

    public void renderGameObjects() {
        batch.enableBlending();
        batch.begin();
        renderBugs();
        batch.end();
    }

    public void renderController() {
        batch.enableBlending();
        batch.begin();
        renderPanel();
        renderProgress(0,Constants.PANEL_HEIGHT,Constants.GAME_WIDTH,world.powerScale.scale/100);
        renderProgress(0,Constants.LIFE_BAR_Y,Constants.LIFE_BAR_LENGTH,world.life/Constants.LIFE);
        batch.end();
    }

    private void renderPanel() {
        batch.draw(Assets.panel,0,0,Constants.GAME_WIDTH,Constants.PANEL_HEIGHT);
        batch.draw(Assets.bug1,0,0,64,80);
        batch.draw(Assets.bug2,64,0,64,80);
        batch.draw(Assets.bug3,128,0,64,80);
    }

    private void renderProgress(float x, float y, float width, float scale) {
        batch.setColor(Color.BLUE);
        batch.draw(Assets.progress,x,y,width*scale,Constants.POWER_BAR_HEIGHT);
        batch.setColor(Color.WHITE);
    }

    private void renderBugs() {
        for (Bug bug : world.bugs) {
            bug.draw(batch);
        }
    }
}

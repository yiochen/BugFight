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
        renderProgress(0,80,Constants.GAME_WIDTH,world.powerScale.scale);
        batch.end();
    }

    private void renderProgress(float x, float y, float width, float scale) {
        batch.draw(Assets.progress,x,y,width*scale/100,40);
    }

    private void renderBugs() {
        for (Bug bug : world.bugs) {
            batch.draw(Assets.bug1, bug.getBounds().getX(), bug.getBounds().getY(), bug.getBounds().getWidth(), bug.getBounds().getHeight());
        }
    }
}

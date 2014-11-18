package yiou.chen.bugfight.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import yiou.chen.bugfight.Assets;
import yiou.chen.bugfight.Constants;
import yiou.chen.bugfight.World;
import yiou.chen.bugfight.object.Bug;
//TODO refactor worldRenderer

/**
 * Created by Yiou on 11/8/2014.
 */
public class WorldRenderer {
    private final SpriteBatch batch;
    private final World world;
    private final OrthographicCamera camera;
    private final BitmapFont font;


    public WorldRenderer(SpriteBatch batch, World world) {
        this.world = world;
        this.batch = batch;
        this.font = Assets.font;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
    }


    public void render() {
        renderGameObjects();
    }


    public void renderGameObjects() {
        renderBugs();
    }


    private void renderBugs() {
        for (Bug bug : world.bugs) {
            bug.draw(batch);
        }
    }

    public void dispose() {

    }
}

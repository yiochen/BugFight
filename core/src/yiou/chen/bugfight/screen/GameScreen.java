package yiou.chen.bugfight.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;

import yiou.chen.bugfight.BugFightGame;
import yiou.chen.bugfight.World;
import yiou.chen.bugfight.object.Renderable;
import yiou.chen.bugfight.object.Updateable;

/**
 * Created by Yiou on 11/8/2014.
 */
public class GameScreen extends ScreenAdapter implements World.WorldListener,Updateable,Renderable{
    private static final int GAME_READY = 0;
    private static final int GAME_RUNNING = 1;
    private static final int GAME_PAUSED = 2;
    private static final int GAME_LEVEL_END = 3;
    private static final int GAME_OVER = 4;
    private final World world;
    private final WorldRenderer render;

    private BugFightGame game;
    private int state;

    public GameScreen(BugFightGame game){
        this.game=game;

        state=GAME_READY;
        this.world=new World(this);
        this.render=new WorldRenderer(game.batch,world);

    }


    /**
     * update the date model
     *
     * @param deltaTime time elapsed.
     */
    @Override
    public void update(float deltaTime) {
        updateRunning(deltaTime);
    }

    private void updateRunning(float deltaTime) {
        world.update(deltaTime);
    }

    @Override
    public void draw() {
        render.render();
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
        //detect user input
        if (Gdx.input.isTouched()){
            world.addBug(1);
        }
    }
}

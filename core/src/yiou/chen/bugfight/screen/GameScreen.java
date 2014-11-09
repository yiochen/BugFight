package yiou.chen.bugfight.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import yiou.chen.bugfight.BugFightGame;
import yiou.chen.bugfight.Constants;
import yiou.chen.bugfight.World;
import yiou.chen.bugfight.object.Bug;
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
        InputListener inputListener=new InputListener();
        Gdx.input.setInputProcessor(inputListener);
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
    public void draw(Batch batch) {
        render.render();
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw(game.batch);
        //detect user input

    }
    private class InputListener extends InputAdapter{
        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            float y=screenY;
            float x=screenX;
            y=Constants.GAME_HEIGHT-y/Gdx.graphics.getHeight()*Constants.GAME_HEIGHT;
            x=x/Gdx.graphics.getWidth()*Constants.GAME_WIDTH;

            Iterator<Bug> it=world.bugs.iterator();
            while(it.hasNext()){
                Bug bug=it.next();
                if (bug.getBounds().contains(x,y)){
                    //play explosion
                    if (!bug.attackBug(Constants.HAND_POWER)){
                        it.remove();
                    }
                }
            }

            for (int i=0; i<world.prototypes.size; i++){

                if (world.prototypes.get(i).getBounds().contains(x,y)) {
                    world.addBug(i);
                }
            }
            return true;
        }
    }
}

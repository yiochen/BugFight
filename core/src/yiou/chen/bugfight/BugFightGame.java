package yiou.chen.bugfight;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import yiou.chen.bugfight.interfaces.BlueToothCallback;
import yiou.chen.bugfight.screen.GameScreen;
import yiou.chen.bugfight.screen.MainScreen;

public class BugFightGame extends Game {
    public final BlueToothCallback blCallback;
    public SpriteBatch batch;


    public BugFightGame(BlueToothCallback callback){
        super();
        this.blCallback=callback;
    }
    public BugFightGame(){
        super();
        blCallback=null;
    }
    @Override
	public void create () {
        //load resources
        Assets.load();

		batch = new SpriteBatch();

        setScreen(new MainScreen(this));


	}



    @Override
	public void render () {
        super.render();


        //user interaction, change bugData.health or something
        //make bug move use Gdx.graphics.getDeltaTime()

        //use iterator to update the bugs
        // Iterator<Rectangle> iter = raindrops.iterator();
//        while(iter.hasNext()) {
//            Rectangle raindrop = iter.next();
//            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
//            if(raindrop.y + 64 < 0) iter.remove();
//            if(raindrop.overlaps(bucket)) {
//                dropSound.play();
//                iter.remove();
//            }
//        }
	}

    @Override
    public void dispose() {
        super.dispose();
        //dispose all texture and music
        this.getScreen().dispose();
    }

}

package yiou.chen.bugfight;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.firebase.client.Firebase;

import yiou.chen.bugfight.interfaces.NetworkCallback;
import yiou.chen.bugfight.object.Room;
import yiou.chen.bugfight.object.User;
import yiou.chen.bugfight.screen.MainScreen;

public class BugFightGame extends Game {
    public final NetworkCallback network;
    public Firebase fb;
    public Room room;
    public User me;
    public SpriteBatch batch;


    public BugFightGame(NetworkCallback callback){
        super();
        this.network =callback;
        this.fb=network.getFb();
    }
    public BugFightGame(){
        super();
        network =null;
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

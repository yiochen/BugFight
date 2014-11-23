package yiou.chen.bugfight;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import yiou.chen.bugfight.interfaces.BluetoothCallback;
import yiou.chen.bugfight.screen.LoginScreen;
import yiou.chen.bugfight.screen.MainScreen;

public class BugFightGame extends Game {
    public final BluetoothCallback blCallback;
    public SpriteBatch batch;


    public BugFightGame(BluetoothCallback callback){
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
	}

    @Override
    public void dispose() {
        super.dispose();
        //dispose all texture and music
        this.getScreen().dispose();
    }

}

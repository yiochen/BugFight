package yiou.chen.bugfight;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class BugFightGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture bug;
    private OrthographicCamera camera;
    private Rectangle bugData;
    private Array<Rectangle> bugArray;
    //use array to save bug data,

    @Override
	public void create () {
        //load resources
        Assets.load();
        bug=Assets.bug1;
        camera=new OrthographicCamera();
        camera.setToOrtho(false,Constants.GAME_WIDTH,Constants.GAME_HEIGHT);
		batch = new SpriteBatch();
        createBug();

	}

    private void createBug() {
        this.bugData=new Rectangle();
        this.bugData.width=64;
        this.bugData.height=80;
        this.bugData.x=0;
        this.bugData.y=0;
    }


    @Override
	public void render () {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
		batch.begin();
        batch.draw(bug,bugData.x,bugData.y);
		batch.end();
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
        bug.dispose();
    }
}

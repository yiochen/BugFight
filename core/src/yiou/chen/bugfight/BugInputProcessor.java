package yiou.chen.bugfight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

import yiou.chen.bugfight.Constants;

/**
 * Created by Yiou on 11/17/2014.
 */
public class BugInputProcessor extends InputAdapter {
    private InputCallback callback;
    public BugInputProcessor(InputCallback callback){
        this.callback=callback;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 vec=new Vector2(screenX,screenY);
        vec=screen2Game(vec);
        return callback.onTouch(vec.x,vec.y);
    }
    public interface InputCallback{
        boolean onTouch(float x, float y);
    }

    public Vector2 screen2Game(Vector2 screenCoor){
        float y = screenCoor.y;
        float x = screenCoor.x;
        y = Constants.GAME_HEIGHT - y / Gdx.graphics.getHeight() * Constants.GAME_HEIGHT;
        x = x / Gdx.graphics.getWidth() * Constants.GAME_WIDTH;
        return new Vector2(x,y);
    }
}
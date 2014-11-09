package yiou.chen.bugfight.object;

import com.badlogic.gdx.math.Vector2;

import yiou.chen.bugfight.interfaces.Updateable;

/**
 * Created by Yiou on 11/8/2014.
 */
public class DynamicGameObject extends GameObject implements Updateable {

    public final Vector2 vel=new Vector2();
    public final Vector2 acc=new Vector2();

    public DynamicGameObject(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public DynamicGameObject(float x, float y, float width, float height, int mode) {
        super(x, y, width, height, mode);
    }


    @Override
    public void update(float deltaTime) {
        this.vel.add(this.acc.x*deltaTime,this.acc.y*deltaTime);
        this.move(this.vel.x*deltaTime,this.vel.y*deltaTime);
    }
}

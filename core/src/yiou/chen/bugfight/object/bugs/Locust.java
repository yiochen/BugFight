package yiou.chen.bugfight.object.bugs;

import com.badlogic.gdx.math.Vector2;

import yiou.chen.bugfight.Assets;

/**
 * fast bug
 * Created by Yiou on 11/9/2014.
 */
public class Locust extends Bug {

    public Locust(float x, float y) {
        super(x, y, Assets.sLocust.x,Assets.sLocust.y);
        this.vel.add(new Vector2(0, -500));
        this.cost=30;
        this.hp=3;
        this.damage=15;
        this.asset= Assets.locust;
    }
}

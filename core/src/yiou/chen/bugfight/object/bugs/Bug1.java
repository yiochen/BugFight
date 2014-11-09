package yiou.chen.bugfight.object.bugs;

import com.badlogic.gdx.math.Vector2;

import yiou.chen.bugfight.Assets;
import yiou.chen.bugfight.object.Bug;

/**
 * normal cheep bug
 * Created by Yiou on 11/9/2014.
 */
public class Bug1 extends Bug {
    public Bug1(float x, float y) {
        super(x, y, 64,80);
        this.vel.add(new Vector2(0,-45));
        this.cost=10;
        this.hp=1;
        this.damage=10;
        this.asset= Assets.bug1;
    }
}

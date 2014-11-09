package yiou.chen.bugfight.object.bugs;

import com.badlogic.gdx.math.Vector2;

import yiou.chen.bugfight.Assets;
import yiou.chen.bugfight.object.Bug;

/**
 * big high hp bug
 * Created by Yiou on 11/9/2014.
 */
public class Bug2 extends Bug {

    public Bug2(float x, float y) {
        super(x, y, 100,130);
        this.vel.add(new Vector2(0, -30));
        this.cost=20;
        this.hp=10;
        this.damage=10;
        this.asset= Assets.bug2;
    }
}

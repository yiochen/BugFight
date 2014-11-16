package yiou.chen.bugfight.object.bugs;

import com.badlogic.gdx.math.Vector2;

import yiou.chen.bugfight.Assets;
import yiou.chen.bugfight.object.Bug;

/**
 * fast bug
 * Created by Yiou on 11/9/2014.
 */
public class Bug3 extends Bug {

    public Bug3(float x, float y) {
        super(x, y, 64,100);
        this.vel.add(new Vector2(0, -200));
        this.cost=30;
        this.hp=3;
        this.damage=15;
        this.asset= Assets.bug3;
    }
}

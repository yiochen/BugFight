package yiou.chen.bugfight.object.bugs;

import com.badlogic.gdx.math.Vector2;

import yiou.chen.bugfight.Assets;

/**
 * big high hp bug
 * Created by Yiou on 11/9/2014.
 */
public class Beetle extends Bug {

    public Beetle(float x, float y) {
        super(x, y, Assets.sBeetle.x,Assets.sBeetle.y);
        this.vel.add(new Vector2(0, -100));
        this.cost=20;
        this.hp=10;
        this.damage=10;
        this.asset= Assets.beetle;
    }
}

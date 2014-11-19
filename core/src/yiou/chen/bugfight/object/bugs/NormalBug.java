package yiou.chen.bugfight.object.bugs;

import com.badlogic.gdx.math.Vector2;

import yiou.chen.bugfight.Assets;

/**
 * normal cheep bug
 * Created by Yiou on 11/9/2014.
 */
public class NormalBug extends Bug {
    public NormalBug(float x, float y) {
        super(x, y, Assets.sNormal.x,Assets.sNormal.y);
        this.vel.add(new Vector2(0,-200));
        this.cost=10;
        this.hp=1;
        this.damage=10;
        this.asset= Assets.normal;
    }
}

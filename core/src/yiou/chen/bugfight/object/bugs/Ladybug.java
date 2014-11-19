package yiou.chen.bugfight.object.bugs;

import com.badlogic.gdx.math.Vector2;

import yiou.chen.bugfight.Assets;

/**
 * Created by Yiou on 11/18/2014.
 */
public class Ladybug extends Bug {
    public Ladybug(float x, float y) {
        super(x, y, Assets.sLady.x, Assets.sLady.y);
        this.vel.add(new Vector2(0, -300));
        this.cost=35;
        this.damage=20;
        this.hp=5;
        this.asset=Assets.ladybug;
    }

    @Override
    public void onTap() {
        this.vel.add(new Vector2(0,-50));
    }
}

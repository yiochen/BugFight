package yiou.chen.bugfight.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import yiou.chen.bugfight.Assets;
import yiou.chen.bugfight.Constants;
import yiou.chen.bugfight.interfaces.Renderable;

/**
 * Created by Yiou on 11/8/2014.
 */
public class Bug extends DynamicGameObject implements Renderable {
    public int hp=1;
    public int cost=10;
    public int damage=10;
    public Texture asset;
    public Bug(float x, float y, float width, float height) {
        super(x, y, width, height, GameObject.TOTAL_INSIDE);
        asset=Assets.bug1;
    }

    /**
     * attack the bug
     * @param damage the damage caused by the attack
     * @return true if the bug is still alive, false, if the bug die after the attack
     */
    public boolean attackBug(int damage){
        hp-=damage;
        return hp>0;
    }
    public boolean reachEnd(){
        float height=this.getBounds().height;
        float bottom=this.getPos().y-height/2;
        return (bottom<= Constants.endLine);
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(asset, getBounds().getX(), getBounds().getY(), getBounds().getWidth(), getBounds().getHeight());
    }
}

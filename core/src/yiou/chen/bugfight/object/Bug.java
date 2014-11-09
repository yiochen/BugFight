package yiou.chen.bugfight.object;

import yiou.chen.bugfight.Constants;

/**
 * Created by Yiou on 11/8/2014.
 */
public class Bug extends DynamicGameObject {
    public int hp=1;
    public Bug(float x, float y, float width, float height) {
        super(x, y, width, height, GameObject.TOTAL_INSIDE);
    }

    /**
     * attack the bug
     * @param damage the damage caused by the attack
     * @return true if the bug is still alive, false, if the bug die after the attack
     */
    public boolean attack(int damage){
        hp-=damage;
        return hp>0;
    }
    public boolean reachEnd(){
        float height=this.getBounds().height;
        float bottom=this.getPos().y-height/2;
        return (bottom<= Constants.endLine);
    }
}

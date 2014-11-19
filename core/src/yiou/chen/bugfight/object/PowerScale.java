package yiou.chen.bugfight.object;

import yiou.chen.bugfight.interfaces.Updateable;

/**
 * A scale to show the power to make bugs, the larger the power, the more or stronger bugs could be made.
 * Created by Yiou on 11/8/2014.
 */
public class PowerScale implements Updateable {
    public float scale=0;
    public float speed;
    /**
     * PowerScale's maximum is 100.
     * @param speed the speed in which the powerScale will increase over time.
     */
    public PowerScale(float speed){
        this.speed=speed;
    }
    /**
     * update the powerScale
     * @param deltaTime time elapsed.
     */
    @Override
    public void update(float deltaTime) {
        scale+=(this.speed*deltaTime);
        fullCheck();
    }

    /**
     * what to do when the scale is full
     * @return true if is full, false if is not full
     */
    protected boolean fullCheck() {
        if (scale>100) {
            scale=100;
            return true;
        }
        return false;
    }
}

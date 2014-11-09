package yiou.chen.bugfight.object;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import yiou.chen.bugfight.Constants;

/**
 * Created by Yiou on 11/8/2014.
 */
public class GameObject {
    public static final int NO_RESTRICTION=0;
    public static final int TOTAL_INSIDE=1;
    public static final int PIVOT_INSIDE=2;

    private Vector2 position;
    private float width;
    private float height;

    private int mode=0;

    public GameObject(float x, float y, float width, float height){
        this.position=new Vector2(x,y);
        this.width=width;
        this.height=height;
    }
    public GameObject(float x, float y, float width, float height, int mode){
        this(x,y,width,height);
        this.mode=mode;
    }
    public Vector2 getPos(){
       return position;
    }
    public Rectangle getBounds(){
        return new Rectangle(position.x-width/2,position.y-height/2,width,height);
    }
    /**
     * move to the absolute position;
     * if it's out of bound according to the mode, the object will not be moved
     * @param x absolute x coordinate
     * @param y absolute y coordinate
     * @return if the object is moved successfully or not.
     */
    public boolean moveTo(float x, float y){
        Vector2 oldPos=new Vector2(this.position);
        this.position.x=x;
        this.position.y=y;
        Rectangle bound=Constants.WorldBound;
        switch (mode){
            case NO_RESTRICTION:break;
            case TOTAL_INSIDE:if (!bound.contains(this.getBounds())) {
                this.position=oldPos;
                return false;
            }
            case PIVOT_INSIDE:if (!bound.contains(this.position)){
                this.position=oldPos;
                return false;
            }
        }
        return true;
    }
    /**
     * move relative units;
     * if it's out of bound according to the mode, the object will not be moved
     * @param x relative x distance
     * @param y relative y distance
     * @return if the object is moved successfully or not.
     */
    public boolean move(float x, float y){
        return moveTo(this.position.x+x,this.position.y+y);
    }
}

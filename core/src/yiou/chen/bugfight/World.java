package yiou.chen.bugfight;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;
import java.util.List;


import yiou.chen.bugfight.object.Bug;
import yiou.chen.bugfight.object.PowerScale;
import yiou.chen.bugfight.object.Updateable;

/**
 * Created by Yiou on 11/8/2014.
 */
public class World implements Updateable{


    private final WorldListener listener;
    public Array<Bug> bugs;
    public PowerScale powerScale;
    public float life;

    public World(WorldListener listener){
        this.listener=listener;
        bugs=new Array<Bug>();
        powerScale=new PowerScale(20);
    }

    /**
     * update the date model
     *
     * @param deltaTime time elapsed.
     */
    @Override
    public void update(float deltaTime) {
        powerScale.update(deltaTime);
        Iterator<Bug> it=bugs.iterator();
        while(it.hasNext()){
            Bug bug=it.next();
            bug.update(deltaTime);
            if (bug.reachEnd()){
                attackHuman(bug);
            }
        }
    }

    public void addBug(int type){

        Bug bug=new Bug(60,900,64,80);
        bug.vel.add(0,-45);
        bugs.add(bug);
    }
    /**
     *
     * @param bug
     * @return true if the player is still alive. false if dead.
     */
    private boolean attackHuman(Bug bug) {
        return false;
    }

    public static interface WorldListener{

    }
}

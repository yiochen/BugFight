package yiou.chen.bugfight;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Iterator;


import yiou.chen.bugfight.object.Bug;
import yiou.chen.bugfight.object.PowerScale;
import yiou.chen.bugfight.interfaces.Updateable;
import yiou.chen.bugfight.object.bugs.Beetle;
import yiou.chen.bugfight.object.bugs.Locust;
import yiou.chen.bugfight.object.bugs.NormalBug;

/**
 * Created by Yiou on 11/8/2014.
 */
public class World implements Updateable{


    private final WorldListener listener;
    public Array<Bug> bugs;
    public PowerScale powerScale;
    public float life=Constants.LIFE;
    public static Array<Constants.BUG> types;
    public static HashMap<Constants.BUG,Bug> prototypes;



    public World(WorldListener listener){
        this.listener=listener;
        bugs=new Array<Bug>();
        makePrototype();
        powerScale=new PowerScale(10);
    }

    private void makePrototype() {
        types =new Array<Constants.BUG>();
        prototypes=new HashMap<Constants.BUG,Bug>();
        types.add(Constants.BUG.NORMAL);
        prototypes.put(Constants.BUG.NORMAL,new NormalBug(0, 0));
        types.add(Constants.BUG.BEETLE);
        prototypes.put(Constants.BUG.BEETLE,new Beetle(0, 0));
        types.add(Constants.BUG.LOCUST);
        prototypes.put(Constants.BUG.LOCUST,new Locust(0, 0));
        types.add(Constants.BUG.LADYBUG);
        prototypes.put(Constants.BUG.LADYBUG,new Bug(0, 0, 0, 0));
    }
    public static Bug type2prototype(Constants.BUG type){
        return prototypes.get(type);
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
        Bug bug;
        float x=(float)Math.random()*(Constants.GAME_WIDTH-Constants.SPOWN_EDGE*2)+Constants.SPOWN_EDGE;
        switch(type){
            case 0:bug=new NormalBug(x,1000);
                break;
            case 1:bug=new Beetle(x,1000);
                break;
            case 2:bug=new Locust(x,1000);
                break;
            default:bug=new NormalBug(x,1000);
                break;
        }

            bugs.add(bug);
        Assets.pang.play();

    }
    /**
     *
     * @param bug
     * @return true if the player is still alive. false if dead.
     */
    private boolean attackHuman(Bug bug) {
        life-=bug.damage;
        Assets.pain.play();
        bugs.removeValue(bug,true);
        return life>0;

    }

    public static interface WorldListener{

    }
}

package yiou.chen.bugfight;

import com.badlogic.gdx.utils.Array;

import java.util.Iterator;


import yiou.chen.bugfight.interfaces.BlueToothCallback;
import yiou.chen.bugfight.object.Bug;
import yiou.chen.bugfight.object.PowerScale;
import yiou.chen.bugfight.interfaces.Updateable;
import yiou.chen.bugfight.object.bugs.Bug1;
import yiou.chen.bugfight.object.bugs.Bug2;
import yiou.chen.bugfight.object.bugs.Bug3;

/**
 * Created by Yiou on 11/8/2014.
 */
public class World implements Updateable{


    private final WorldListener listener;
    public Array<Bug> bugs;
    public PowerScale powerScale;
    public float life=Constants.LIFE;
    public Array<Bug> prototypes;


    public World(WorldListener listener){
        this.listener=listener;
        bugs=new Array<Bug>();
        makePrototype();
        powerScale=new PowerScale(10);
    }

    private void makePrototype() {
        prototypes=new Array<Bug>();
        prototypes.add(new Bug1(100,70));
        prototypes.add(new Bug2(200,70));
        prototypes.add(new Bug3(300,70));
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
            case 0:bug=new Bug1(x,1000);
                break;
            case 1:bug=new Bug2(x,1000);
                break;
            case 2:bug=new Bug3(x,1000);
                break;
            default:bug=new Bug1(x,1000);
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

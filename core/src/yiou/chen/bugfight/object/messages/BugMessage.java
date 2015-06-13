package yiou.chen.bugfight.object.messages;

import com.firebase.client.DataSnapshot;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yiou on 6/12/2015.
 */
public class BugMessage {
    private int type;
    private int vel;
    private int hp;
    private int damage;
    private String userid;
    public BugMessage(){}
    public BugMessage(String userid, int type,int vel, int hp, int dmg){
        this.userid=userid;
        this.type=type;
        this.vel=vel;
        this.hp=hp;
        this.damage=dmg;
    }
    public static BugMessage fromSnapshot(DataSnapshot snapshot){
        HashMap<String,Object> value=(HashMap<String, Object>)snapshot.getValue();
        BugMessage m=new BugMessage(value.get("userid").toString(),(int)value.get("type"),(int)value.get("vel"),(int)value.get("hp"),(int)value.get("damage"));
        return m;
    }

    public String getUserid() {
        return userid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getVel() {
        return vel;
    }

    public void setVel(int vel) {
        this.vel = vel;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}

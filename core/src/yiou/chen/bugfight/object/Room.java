package yiou.chen.bugfight.object;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

import yiou.chen.bugfight.object.messages.BugMessage;

/**
 * Created by Yiou on 6/12/2015.
 */
public class Room {
    public String uuid;
    public Firebase roomRef;
    private Firebase postsRef;
    public Room(Firebase roomRef){
        this.roomRef=roomRef;
        postsRef=roomRef.child("posts");
        this.uuid=roomRef.getKey();
    }
    public String send(BugMessage m){
        Firebase newPost=postsRef.push();
        newPost.setValue(m);
        return newPost.getKey();
    }
    public void setStatus(int status){
        Map<String, Object> s=new HashMap<>();
        s.put("status",status);
        roomRef.updateChildren(s);
    }
    public Firebase getRoomRef(){
        return this.roomRef;
    }
    public Firebase getPostsRef(){
        return this.postsRef;
    }
}

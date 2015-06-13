package yiou.chen.bugfight.object.messages;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yiou on 6/12/2015.
 */
public class RoomMessage {
    public String roomId;
    private String hostname;
    private String hostuuid;
    private int status;
    public static final int AVAILABLE=0;
    public static final int BUSY=1;


    public RoomMessage(){}
    public RoomMessage(String hostname,String hostuuid){
        this.hostname=hostname;
        this.hostuuid=hostuuid;
        this.status=AVAILABLE;
    }
    public String getHostname() {
        return hostname;
    }
    public String getHostuuid(){
        return hostuuid;
    }
    public static RoomMessage fromSnapshot(DataSnapshot snapshot){
        Map<String, Object> value=(HashMap<String,Object>)snapshot.getValue();
        RoomMessage m=new RoomMessage(value.get("hostname").toString(),value.get("hostuuid").toString());
        m.roomId=snapshot.getKey();
        m.status=(int)value.get("status");
        return m;
    }
}

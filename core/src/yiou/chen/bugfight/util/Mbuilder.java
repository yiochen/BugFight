package yiou.chen.bugfight.util;

import yiou.chen.bugfight.util.BugMessageProto.BugMessage;
import yiou.chen.bugfight.util.BugMessageProto.BugMessage.*;
/**
 * Created by Yiou on 11/22/2014.
 */
public class Mbuilder {
    public static BugMessage newBug(BugType type){
        BugMessage.Builder message=BugMessage.newBuilder();
        BugPro.Builder bug=BugPro.newBuilder();
        bug.setType(type);
        message.setBug(bug);
        return message.build();
    }
    public static BugType getBugType(BugMessage message){
        if (message!=null && message.getBug()!=null){
            return message.getBug().getType();
        }else return null;
    }
    public static BugMessage newState(StateType type){
        BugMessage.Builder message=BugMessage.newBuilder();
        StatePro.Builder state=StatePro.newBuilder();
        state.setType(type);
        message.setState(state);
        return message.build();
    }
    public static StateType getState(BugMessage message){
        if (message!=null && message.getState()!=null){
            return message.getState().getType();
        }else return null;
    }


}

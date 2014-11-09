package yiou.chen.bugfight.object;

/**
 * Created by Yiou on 11/9/2014.
 */
public class BugPrototype {
    private boolean enabled;

    public BugPrototype(Class bugClass){

    }
    public void setEnabled(boolean enabled){
        this.enabled=enabled;
    }
    public boolean isEnabled(){
        return this.enabled;
    }
}

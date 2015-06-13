package yiou.chen.bugfight.object;

/**
 * Created by Yiou on 6/12/2015.
 */
public class User {
    private String name;
    private String uuid;

    public User(){}
    public User(String name, String uuid){
        this.name=name;
        this.uuid=uuid;

    }
    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }


}

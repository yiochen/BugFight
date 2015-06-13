package yiou.chen.bugfight.android;

import android.app.Activity;

import com.firebase.client.Firebase;

import yiou.chen.bugfight.interfaces.NetworkCallback;

/**
 * Created by Yiou on 6/12/2015.
 */
public class AndroidFirebase implements NetworkCallback{
    private Firebase fb;
    public AndroidFirebase(Activity context){
        init(context);
    }
    public void init(Activity context){
        Firebase.setAndroidContext(context);
        Firebase fb=new Firebase("https://bugmenot.firebaseIO.com/");
    }
    public Firebase getFb(){
        return this.fb;
    }
}

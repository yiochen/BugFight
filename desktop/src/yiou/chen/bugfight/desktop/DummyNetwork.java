package yiou.chen.bugfight.desktop;

import java.util.ArrayList;
import java.util.List;

import yiou.chen.bugfight.interfaces.NetworkCallback;

/**
 * Created by Yiou on 11/17/2014.
 */
public class DummyNetwork implements NetworkCallback {
    private int bug=0;
    private boolean started=false;
    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {

    }

    @Override
    public boolean isBluetoothOn() {
        return true;
    }

    @Override
    public List<String> getPaiedList() {
        return new ArrayList<String>();
    }

    @Override
    public void chooseDevice(String description) {

    }

    @Override
    public void openServer() {

    }

    @Override
    public void connectAsClient() {

    }

    @Override
    public void write(int value) {
        bug=value;
    }

    @Override
    public int read() {
        if (!started){
            started=true;
            return 10;
        }
        if (bug!=0) {
            int tmp=bug;
            bug=0;
            return tmp;
        }
        else return 0;
    }

    @Override
    public void cancelAccept() {

    }

    @Override
    public void cancelConnect() {

    }

    @Override
    public void cancelTransfer() {

    }

    @Override
    public boolean isConnected() {
        return true;
    }
}

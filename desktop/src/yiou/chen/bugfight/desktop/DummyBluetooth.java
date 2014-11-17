package yiou.chen.bugfight.desktop;

import java.util.ArrayList;
import java.util.List;

import yiou.chen.bugfight.interfaces.BluetoothCallback;

/**
 * Created by Yiou on 11/17/2014.
 */
public class DummyBluetooth implements BluetoothCallback {
    private int bug=0;

    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {

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

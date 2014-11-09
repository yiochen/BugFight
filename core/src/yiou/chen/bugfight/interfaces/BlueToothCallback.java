package yiou.chen.bugfight.interfaces;

import java.util.List;

/**
 * Created by Yiou on 11/9/2014.
 */
public interface BlueToothCallback {
    void turnOn();
    void turnOff();
    List<String> getPaiedList();
    void chooseDevice(String description);
    void openServer();
    void connectAsClient();
    void write(int value);
    int read();
    void cancelAccept();
    void cancelConnect();
    void cancelTransfer();
    boolean isConnected();
}

package yiou.chen.bugfight.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import yiou.chen.bugfight.interfaces.NetworkCallback;

/**
 * Created by Yiou on 11/9/2014.
 */
public class AndroidNetwork implements NetworkCallback {
    private boolean isConnected=false;
    private final int timeOut = 60000;
    private static final String SERVICE_NAME = "network lets connect";
    private static final String STRING_UUID = "2bae675b-5999-4cc2-ae9c-247b68e20334";

    private final BluetoothAdapter mBluetoothAdapter;
    private final String TAG = "Bluetooth";
    private Activity context;
    private boolean blAvailable;
    private Set<BluetoothDevice> pairedDevices;
    private BluetoothDevice mChosenDevice;
    private TransferThread mTransferThread;
    private AcceptThread acceptThread;
    private ConnectThread connectThread;
    private Handler uiThread = AndroidLauncher.uiThread;

    public AndroidNetwork(Activity context) {

        //take an instance of BluetoothAdapter - Bluetooth radio
        this.context = context;
        pairedDevices = new HashSet<BluetoothDevice>();
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Log.e(TAG, "your device doesn't support network");
            blAvailable = false;
        } else {
            blAvailable = true;
        }



    }

    public void connect(String user){

    }
    public void makeToast(final CharSequence sequence,final int length){
        uiThread.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(AndroidNetwork.this.context, sequence, length).show();
            }
        });
    }
    @Override
    public void turnOn() {

        if (!mBluetoothAdapter.isEnabled()) {
            uiThread.post(new Runnable() {
                /**
                 * Starts executing the active part of the class' code. This method is
                 * called when a thread is started that has been created with a class which
                 * implements {@code Runnable}.
                 */
                @Override
                public void run() {
                    Toast.makeText(AndroidNetwork.this.context, "turning on network", Toast.LENGTH_LONG).show();
                    Intent turnOnIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    context.startActivityForResult(turnOnIntent, 1);
                }
            });
        } else {
            uiThread.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(AndroidNetwork.this.context, "Bluetooth failed", Toast.LENGTH_LONG).show();
                }
            });

            Log.i(TAG, "as I told you network not available");
        }
    }

    @Override
    public boolean isBluetoothOn() {

        return mBluetoothAdapter.isEnabled();

    }

    @Override
    public void turnOff() {
        mBluetoothAdapter.disable();
    }

    @Override
    public List<String> getPaiedList() {
        pairedDevices = mBluetoothAdapter.getBondedDevices();
        ArrayList<String> tmp = new ArrayList<String>();
        for (BluetoothDevice device : pairedDevices) {
            tmp.add(getDescription(device));
        }
        return tmp;
    }

    public String getDescription(BluetoothDevice device) {
        return device.getName() + "\n" + device.getAddress();
    }

    @Override
    public void chooseDevice(String description) {

        for (BluetoothDevice device : pairedDevices) {
            if (getDescription(device).equals(description)) {
                mChosenDevice = device;
                return;
            }
        }

    }

    @Override
    public void openServer() {
        acceptThread = new AcceptThread();
        acceptThread.start();
    }

    @Override
    public void connectAsClient() {
        if (mChosenDevice != null) {
            connectThread = new ConnectThread(mChosenDevice);
            connectThread.start();

        } else {
            Log.e("TAG", "please choose a server device first");
        }
    }

    @Override
    public void write(int value) {
        if (mTransferThread != null) {
            mTransferThread.write(value);
        }
    }

    @Override
    public int read() {
        if (mTransferThread != null) {
            return mTransferThread.read();
        }
        return 0;
    }


    @Override
    public void cancelAccept() {
        acceptThread.cancel();
    }

    @Override
    public void cancelConnect() {
        isConnected = false;
        connectThread.cancel();
    }

    @Override
    public void cancelTransfer() {
        isConnected = false;
        mTransferThread.cancel();
    }

    @Override
    public boolean isConnected() {
        return isConnected;
    }

    /**
     * Used for server side only
     * When a Socket is returned by the accept() method,
     * the connection is already formed, no need to call connect(),
     * as you do from the client side.
     */
    private class AcceptThread extends Thread {
        private final BluetoothServerSocket mServerSocket;

        public AcceptThread() {
            BluetoothServerSocket tmp = null;

            try {
                tmp = mBluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord(SERVICE_NAME, UUID.fromString(STRING_UUID));
                Log.i("Server", "server created!");
                isConnected = true;
            } catch (IOException e) {
                isConnected = false;
                e.printStackTrace();
            }
            mServerSocket = tmp;
        }

        public void run() {

            BluetoothSocket socket = null;
            //Keep listening until exception occurs or a socket is returned
            while (true) {
                try {
                    if (mServerSocket!=null) {
                        socket = mServerSocket.accept();
                    }
                } catch (IOException e) {
                    break;
                }
                if (socket != null) {

                    //Do work to manage the connection (in a seperate thread)

                    try {
                        mServerSocket.close();
                        Log.i("Server", "client connected!!");
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
            mTransferThread = new TransferThread(socket);
            mTransferThread.start();
        }

        /**
         * will cancel the listening socket, anc cause the thread to finish
         */
        public void cancel() {
            try {

                mServerSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Client side. run this thread only when the device discovery is complete.
     * otherwise the connection attempt will be significantly slowed and is more likely to fail.
     */
    private class ConnectThread extends Thread {
        private final BluetoothSocket mSocket;
        private final BluetoothDevice mDevice;

        public ConnectThread(BluetoothDevice device) {
            BluetoothSocket tmp = null;
            mDevice = device;
            //Get a BluetoothSocket to connect with the given BluetoothDevice
            try {
                tmp = device.createRfcommSocketToServiceRecord(UUID.fromString(STRING_UUID));
                isConnected = false;
            } catch (IOException e) {
                isConnected=false;
                e.printStackTrace();
            }
            mSocket = tmp;
        }

        @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
        public void run() {
            //Cancel discovery because it will slow down the connection

            mBluetoothAdapter.cancelDiscovery();
            Log.i("Client", "trying to connect to server");
            try {
                mSocket.connect();
                Log.i("Client", "connected");

                isConnected = mSocket.isConnected();

            } catch (IOException connectException) {
                Log.i("Client", "fail io connect");
                isConnected = false;

                try {
                    mSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
            //manageConnectedSocket(mSocket);
            if (isConnected()) {
                mTransferThread = new TransferThread(mSocket);
                mTransferThread.start();
            }else{
                makeToast("Please try connecting again",Toast.LENGTH_LONG);
            }
        }

        public void cancel() {
            try {
                if (mTransferThread != null) mTransferThread.cancel();
                mSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private class TransferThread extends Thread {
        private final BluetoothSocket mSocket;
        private final DataInputStream mInStream;
        private final DataOutputStream mOutStream;
        public int readInInt = 0;

        public TransferThread(BluetoothSocket socket) {
            mSocket = socket;
            DataInputStream tmpIn = null;

            DataOutputStream tmpOut = null;

            try {
                tmpIn = new DataInputStream(socket.getInputStream());

                tmpOut = new DataOutputStream(socket.getOutputStream());

            } catch (IOException e) {
                e.printStackTrace();
            }
            mInStream = tmpIn;
            mOutStream = tmpOut;
        }

        @Override
        public void run() {
            //byte[] buffer = new byte[1024]; //buffer store for the stream
            int bytes;

            //keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    //Read from the InputStrea,
                    bytes = mInStream.readInt();
                    readInInt = bytes;
                    //Send the obtained bytes to the UI activity;
                    Log.i("TRANSFER", "received " + bytes);
                } catch (IOException e) {
                    // e.printStackTrace();
                    break;
                }
            }
        }

        /**
         * @return default return 0 if no data is read
         */
        public int read() {
            int ans = readInInt;
            readInInt = 0;
            return ans;
        }

        /**
         * Call this from the main activity to send data to the remote device
         */

        public void write(int ints) {
            try {
                mOutStream.writeInt(ints);
                Log.i("TRANSFER", "wrote " + ints);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Call this from the main activity to shutdown the connection
         */
        public void cancel() {
            try {
                mSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

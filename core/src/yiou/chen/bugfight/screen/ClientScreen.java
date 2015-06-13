package yiou.chen.bugfight.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import yiou.chen.bugfight.Assets;
import yiou.chen.bugfight.BugFightGame;
import yiou.chen.bugfight.Constants;
import yiou.chen.bugfight.object.Room;
import yiou.chen.bugfight.object.User;
import yiou.chen.bugfight.object.messages.RoomMessage;

/**
 * Created by Yiou on 11/9/2014.
 */
public class ClientScreen extends AbstractScreen {
    //show a list of rooms
    private List<Rectangle> bounds;
    private List<RoomMessage> rooms;
    private Firebase fb;

    //register a user
    //show the list of all connected players


    public ClientScreen(BugFightGame game) {
        super(game);
        this.fb = game.fb;
//        if (!network.isBluetoothOn()) {
//            network.turnOn();
//
//        }

        this.bounds = new ArrayList<Rectangle>();
        this.rooms = new ArrayList<RoomMessage>();
        getRooms();

    }

    private void getRooms() {
        fb.child("users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String s) {
                rooms.add(RoomMessage.fromSnapshot(snapshot));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    protected void drawBackground() {
        super.drawBackground();
        drawBatch(Assets.grass, Assets.rGrass);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
//        if (status == Constants.STATUS.PREPARE && network != null && network.isBluetoothOn()) {
//
//            names = network.getPaiedList();
//            status=Constants.STATUS.RUNNING;
//        }
//        if (status==Constants.STATUS.RUNNING && network !=null&& network.isBluetoothOn()){
//            names = network.getPaiedList();
//        }
//        if (status == Constants.STATUS.BL_OK && network != null && network.isBluetoothOn() && network.isConnected()) {
//            network.write(10);
//            game.setScreen(new GameScreen(game));
//        }
        batch.begin();
        draw(batch);
        batch.end();
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        drawList();
    }

    private void drawList() {
        font.setColor(Color.WHITE);
        font.setScale(1, 1);
        for (int i = 0; i < rooms.size(); i++) {
            Rectangle bound = drawLeftText(rooms.get(i).getHostname(), 1200 - i * 104, 10, Constants.TEXT_M);
            bounds.add(bound);
        }
    }

    @Override
    public boolean onTouch(float x, float y) {
        for (int i = 0; i < bounds.size(); i++) {
            if (bounds.get(i).contains(x, y)) {
                Room room = new Room(fb.child("rooms").child(rooms.get(i).roomId));
                //set room status to busy
                room.setStatus(RoomMessage.BUSY);
                game.room = room;
                game.setScreen(new GameScreen(game));
                return true;
            }
        }
        return true;
    }
}

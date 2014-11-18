package yiou.chen.bugfight.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;


import java.util.HashMap;
import java.util.Iterator;

import yiou.chen.bugfight.Assets;
import yiou.chen.bugfight.BugFightGame;
import yiou.chen.bugfight.Constants;
import yiou.chen.bugfight.World;
import yiou.chen.bugfight.object.Bug;
import yiou.chen.bugfight.interfaces.Updateable;
import yiou.chen.bugfight.object.bugs.Beetle;
import yiou.chen.bugfight.object.bugs.Locust;
import yiou.chen.bugfight.object.bugs.NormalBug;

/**
 * Created by Yiou on 11/8/2014.
 */
public class GameScreen extends AbstractScreen implements World.WorldListener,Updateable{

    private final World world;
    private final WorldRenderer render;
    private HashMap<Constants.BUG,Texture> prototypeUI;
    private HashMap<Constants.BUG,Rectangle> prototypeRect;



    public GameScreen(BugFightGame game){
        super(game);
        this.world=new World(this);
        initPrototype();
        this.render=new WorldRenderer(game.batch,world);
        status= Constants.STATUS.RUNNING;
    }

    private void initPrototype() {
        prototypeUI=new HashMap<Constants.BUG, Texture>();
        prototypeUI.put(Constants.BUG.NORMAL, Assets.normal_p);
        prototypeUI.put(Constants.BUG.BEETLE,Assets.beetle_p);
        prototypeUI.put(Constants.BUG.LOCUST,Assets.locust_p);
        prototypeUI.put(Constants.BUG.LADYBUG,Assets.ladybug_p);

        prototypeRect=new HashMap<Constants.BUG, Rectangle>();
        prototypeRect.put(Constants.BUG.NORMAL,Assets.rNormal);
        prototypeRect.put(Constants.BUG.BEETLE,Assets.rBeetle);
        prototypeRect.put(Constants.BUG.LOCUST,Assets.rLocust);
        prototypeRect.put(Constants.BUG.LADYBUG,Assets.rLady);
    }

    /**
     * update the date model
     *
     * @param deltaTime time elapsed.
     */
    @Override
    public void update(float deltaTime) {
        switch (status){
            case RUNNING: updateRunning(deltaTime);
                break;
            case PAUSE:updatePause(deltaTime);
                break;
        }

    }
    private void updatePause(float deltaTime){

    }
    private void updateRunning(float deltaTime) {
        int tmp=bluetooth.read();
        if (tmp>0){
            world.addBug(tmp-1);
        }
        world.update(deltaTime);
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        switch (status){
            case RUNNING: render.render();
                drawController();
                break;
            case PAUSE:render.render();
                break;
        }

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);

        batch.enableBlending();
        batch.begin();
        draw(game.batch);
        batch.end();
        batch.disableBlending();
    }

    public void drawController() {
        drawPanel();
        renderPower(world.powerScale.scale / 100);
        font.setColor(Color.WHITE);
        font.setScale((float)1,(float)2);
        font.draw(batch,"HP",0,Constants.LIFE_BAR_Y);
        drawBatch(Assets.hpPanel,Assets.rHPPanel);
        renderLife(world.life / Constants.LIFE, 0.5f);
        drawBatch(Assets.hpBar,Assets.rHPBar);
        drawBatch(Assets.hpIndicator,Assets.rHPIndicator);

    }
    private void drawPrototype(Constants.BUG type,float power){
        Bug bug=World.type2prototype(type);
        Color color=batch.getColor();
        if (power<bug.cost){
            batch.setColor(Color.DARK_GRAY);
        }else{
            batch.setColor(Color.WHITE);
        }
        drawBatch(prototypeUI.get(type),prototypeRect.get(type));
        batch.setColor(color);
    }
    private void drawPanel() {
        drawBatch(Assets.bugPanel,Assets.rBugPanel);
        for (Constants.BUG bug:world.types){
           drawPrototype(bug,world.powerScale.scale);
        }
    }
    private void renderLife(float myLife,float yourLife){

    }
    private void renderPower(float scale) {
        drawBatch(Assets.powerBackground,Assets.rPowerBackground);
        drawBatch(Assets.power,Assets.rPower,scale);

    }

    @Override
    public boolean onTouch(float x, float y) {
        Iterator<Bug> it=world.bugs.iterator();
        while(it.hasNext()){
            Bug bug=it.next();
            if (bug.getBounds().contains(x,y)){
                //play explosion
                Assets.slap.play();
                if (!bug.attackBug(Constants.HAND_POWER)){
                    it.remove();
                }
            }
        }

        for (Constants.BUG type:World.types){

            if (getFlipYRect(prototypeRect.get(type)).contains(x,y)) {
                //world.addBug(i);
                Bug bug=World.type2prototype(type);

                if (world.powerScale.scale>=bug.cost){
                    world.powerScale.scale-=bug.cost;
                    bluetooth.write(type.ordinal()+1);
                }

            }
        }
        return true;
    }

    @Override
    public void dispose() {
        super.dispose();
        render.dispose();
        bluetooth.cancelTransfer();
    }
}

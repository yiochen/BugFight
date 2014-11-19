package yiou.chen.bugfight.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import yiou.chen.bugfight.Assets;
import yiou.chen.bugfight.BugFightGame;
import yiou.chen.bugfight.BugInputProcessor;
import yiou.chen.bugfight.Constants;
import yiou.chen.bugfight.interfaces.BluetoothCallback;
import yiou.chen.bugfight.interfaces.Renderable;

/**
 * Created by Yiou on 11/9/2014.
 */
public abstract class AbstractScreen extends ScreenAdapter implements Renderable, BugInputProcessor.InputCallback {
    protected final BugFightGame game;
    protected Constants.STATUS status= Constants.STATUS.PREPARE;
    protected final BluetoothCallback bluetooth;
    protected OrthographicCamera camera;
    protected BitmapFont font;
    protected SpriteBatch batch;
    public AbstractScreen(BugFightGame game){
        this.game=game;
        this.bluetooth=game.blCallback;
        Gdx.input.setInputProcessor(new BugInputProcessor(this));
        renderSetup();
    }

    private void renderSetup() {
        this.batch=game.batch;
        camera=new OrthographicCamera();
        camera.setToOrtho(false, Constants.GAME_WIDTH,Constants.GAME_HEIGHT);
        this.font= Assets.font;
    }


    @Override
    public void draw(Batch batch) {
        drawBackground();
    }
    protected void drawBackground(){
        batch.draw(Assets.background, 0, 0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
    }
    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        updateBluetooth();
    }
    protected float flipY(Rectangle rectangle){
        return Constants.GAME_HEIGHT-rectangle.y-rectangle.height;
    }
    public Rectangle getFlipYRect(Rectangle rectangle){
        Rectangle rect=new Rectangle(rectangle);
        rect.setY(flipY(rectangle));
        return rect;
    }
    protected void drawBatch(Texture texture,Rectangle rectangle){
        batch.draw(texture,rectangle.x,flipY(rectangle),rectangle.width,rectangle.height);
    }
    protected void drawBatch(Texture texture, Rectangle rectangle,float xScale){
        Rectangle rect=new Rectangle(rectangle);
        rect=rect.setWidth(rect.width*xScale);
        drawBatch(texture, rect);
    }
    private void updateBluetooth() {

    }
    public Rectangle drawCenterText(CharSequence seq,int topY,float scale){
        font.setScale(scale);

        BitmapFont.TextBounds bounds=font.getBounds(seq);
        float width=bounds.width;
        float height=bounds.height;
        float x=((float)Constants.GAME_WIDTH-width)/2;
        float y=topY-height;
        font.draw(batch,seq,x,topY);
        return new Rectangle(x,y,width,height);
    }
    public Rectangle drawLeftText(CharSequence seq, int topY, int leftMargin, float scale){
        font.setScale(scale);

        BitmapFont.TextBounds bounds=font.getBounds(seq);
        float width=bounds.width;
        float height=bounds.height;
        float x=leftMargin;
        float y=topY-height;
        font.draw(batch,seq,x,topY);
        return new Rectangle(x,y,width,height);
    }
}

package game.ipca.spacefighter.sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import game.ipca.spacefighter.R;
import game.ipca.spacefighter.Sprite;

/**
 * Created by lourencogomes on 13/11/17.
 */

public class Player  extends Sprite{

    private int speed=0;
    private boolean boosting=false;

    private final int GRAVITY = -10;
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;

    public Player(Bitmap bitmap, int screenX, int screenY) {
        super(bitmap, screenX, screenY);

        this.bitmap=bitmap;
        x=75;
        y=50;
        speed=1;

        maxY = screenY - bitmap.getHeight();
        minY = 0;
    }

    public int getSpeed() {
        return speed;
    }

    public void setBoosting(){
        boosting=true;
    }

    public void stopBoosting(){
        boosting=false;
    }


    @Override
    public void update(int spd){
        super.update(spd);

        if(boosting)speed +=2;
        else speed -=5;

        if (speed > MAX_SPEED) speed=MAX_SPEED;
        if (speed < MIN_SPEED) speed=MIN_SPEED;

        y -= speed + GRAVITY;

        if (y < minY) y = minY;
        if (y > maxY) y = maxY;

    }
}

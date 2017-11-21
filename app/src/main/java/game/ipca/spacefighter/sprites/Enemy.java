package game.ipca.spacefighter.sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

import game.ipca.spacefighter.R;
import game.ipca.spacefighter.Sprite;

/**
 * Created by lourencogomes on 14/11/17.
 */

public class Enemy extends Sprite{

    private int speed=1;

    public Enemy(Bitmap bitmap, int screenX, int screenY){
        super(bitmap,screenX,screenY);
        this.bitmap=bitmap;
        //bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);

        maxX=screenX;
        maxY=screenY;
        minX=0;
        minY=0;

        Random generator=new Random();
        speed = generator.nextInt(6) + 10;

        x = screenX;
        y = generator.nextInt(maxY)-bitmap.getHeight();

    }

    @Override
    public void update(int playerSpeed){
        super.update(playerSpeed);
        x -= playerSpeed;
        x -= speed;

        if (x < 0 - bitmap.getWidth()){
            x = maxX;
            Random generator= new Random();
            y = generator.nextInt(maxY) - -bitmap.getHeight();
            speed = generator.nextInt(10) + 10;
        }
    }





}

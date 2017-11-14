package game.ipca.spacefighter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

/**
 * Created by lourencogomes on 14/11/17.
 */

public class Enemy {

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int x;
    private int y;
    private int speed=1;
    private int maxY;
    private int minY;
    private int maxX;
    private int minX;

    public Bitmap getBitmap() {
        return bitmap;
    }

    private Bitmap bitmap;

    public Enemy(Context context, int screenX, int screenY){

        bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);

        maxX=screenX;
        maxY=screenY;
        minX=0;
        minY=0;

        Random generator=new Random();
        speed = generator.nextInt(6) + 10;

        x = screenX;
        y = generator.nextInt(maxY)-bitmap.getHeight();
    }

    public void update(int playerSpeed){
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

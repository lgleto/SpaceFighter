package game.ipca.spacefighter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by lourencogomes on 21/11/17.
 */

public class Boom {

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private Bitmap bitmap;
    private int x;
    private int y;

    public Boom(Context context){

        bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.boom);

        x= - 250;
        y= - 250;
    }
}

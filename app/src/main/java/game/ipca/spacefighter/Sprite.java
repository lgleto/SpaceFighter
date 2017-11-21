package game.ipca.spacefighter;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by lourencogomes on 21/11/17.
 */

public class Sprite {

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    protected int x;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    protected int y;
    protected int maxY;
    protected int minY;
    protected int maxX;
    protected int minX;
    protected Rect detectCollision;
    protected Bitmap bitmap;

    public Sprite (Bitmap bitmap, int screenX, int screenY){

        this.bitmap = bitmap;

        maxX=screenX;
        maxY=screenY;
        minX=0;
        minY=0;

        detectCollision= new Rect (x,y, bitmap.getWidth(), bitmap.getHeight());
    }

    public Sprite ( int screenX, int screenY){

        maxX=screenX;
        maxY=screenY;
        minX=0;
        minY=0;

        if (bitmap!=null)
            detectCollision= new Rect (x,y, bitmap.getWidth(), bitmap.getHeight());
    }

    public Rect getDetectCollision() {
        return detectCollision;
    }

    public void update(int player){
        if (bitmap!=null) {
            detectCollision.left = x;
            detectCollision.top = y;
            detectCollision.right = x + bitmap.getWidth();
            detectCollision.bottom = y + bitmap.getHeight();
        }
    }


}

package game.ipca.spacefighter.sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

import game.ipca.spacefighter.R;
import game.ipca.spacefighter.Sprite;

/**
 * Created by lourencogomes on 21/11/17.
 */

public class Boom extends Sprite{

    public Boom(Bitmap bitmap, int screenX, int screenY){
        super(bitmap,screenX,screenY);
        this.bitmap=bitmap;
        x= - 250;
        y= - 250;
    }
}

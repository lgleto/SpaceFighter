package game.ipca.spacefighter.sprites;

import java.util.Random;

import game.ipca.spacefighter.Sprite;

/**
 * Created by lourencogomes on 14/11/17.
 */

public class Star extends Sprite {


    private int speed=0;

    public Star (int screenX, int screenY){
        super(screenX,screenY);
        Random generator=new Random();
        speed = generator.nextInt(10);
        x = generator.nextInt(maxX);
        y = generator.nextInt(maxY);
    }

    @Override
    public void update(int playerSpeed){
        super.update(playerSpeed);
        x -= playerSpeed;
        x -= speed;

        if (x < 0 ){

            x = maxX;
            Random generator= new Random();
            y = generator.nextInt(maxY);
            speed = generator.nextInt(10);

        }
    }

    public float getStarWidth() {
        Random generator=new Random();
        float finalX= generator.nextFloat()*4.0f;
        return finalX;
    }




}

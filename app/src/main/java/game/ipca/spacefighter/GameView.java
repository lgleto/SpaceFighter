package game.ipca.spacefighter;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import game.ipca.spacefighter.sprites.Boom;
import game.ipca.spacefighter.sprites.Enemy;
import game.ipca.spacefighter.sprites.Player;
import game.ipca.spacefighter.sprites.Star;

/**
 * Created by lourencogomes on 13/11/17.
 */

public class GameView extends SurfaceView implements Runnable {

    private boolean playing = false;

    private Thread gameThread = null;



    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    private Player player;
    private ArrayList<Star> stars = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Boom boom;

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        player=new Player(BitmapFactory.decodeResource(context.getResources(), R.drawable.player),screenX,screenY);
        boom = new Boom(BitmapFactory.decodeResource(context.getResources(), R.drawable.boom), 0,0);

        paint=new Paint();
        surfaceHolder=getHolder();

        for (int i=0; i<100;i++){
            Star s =new Star(screenX,screenY);
            stars.add(s);
        }

        for (int i=0; i<3;i++){
            Enemy enemy=new Enemy(BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy),screenX,screenY);
            enemies.add(enemy);
        }
    }



    @Override
    public void run() {
        while (playing){
            update();
            draw();
            control();
        }
    }

    private void update() {
        player.update(0);

        boom.setX(-250);
        boom.setY(-250);

        for (Star s:stars){
            s.update(player.getSpeed());
        }

        for (Enemy e:enemies){
            e.update(player.getSpeed());

            if (Rect.intersects(player.getDetectCollision(),e.getDetectCollision())){
                boom.setX(e.getX());
                boom.setY(e.getY());
                e.setX(-200);

            }
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()){
            canvas=surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            paint.setColor(Color.WHITE);

            for (Star s: stars){
                paint.setStrokeWidth(s.getStarWidth());
                canvas.drawPoint(s.getX(),s.getY(),paint);
            }

            for (Enemy e: enemies){
                canvas.drawBitmap(e.getBitmap(),e.getX(),e.getY(),paint);
            }


            canvas.drawBitmap(player.getBitmap(),
                    player.getX(),
                    player.getY(),
                    paint);

            canvas.drawBitmap(boom.getBitmap(),boom.getX(),boom.getY(),paint);

            surfaceHolder.unlockCanvasAndPost(canvas);

        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause(){
        playing = false;
        try{
            gameThread.join();
        }catch (InterruptedException e){

        }
    }

    public void resume(){
        playing=true;
        gameThread=new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()&MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_UP:
                player.stopBoosting();
                Log.d("SpaceFighter", "up");
                break;
            case MotionEvent.ACTION_DOWN:
                player.setBoosting();
                Log.d("SpaceFighter", "down");
                break;
        }
        return true;
    }
}

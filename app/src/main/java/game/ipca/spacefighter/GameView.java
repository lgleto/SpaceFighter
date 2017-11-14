package game.ipca.spacefighter;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by lourencogomes on 13/11/17.
 */

public class GameView extends SurfaceView implements Runnable {

    private boolean playing = false;

    private Thread gameThread = null;

    private Player player;

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    private ArrayList<Star> stars = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public GameView(Context context, int screenX, int screenY) {
        super(context);
        player=new Player(context,screenX,screenY);

        paint=new Paint();
        surfaceHolder=getHolder();

        for (int i=0; i<100;i++){
            Star s =new Star(screenX,screenY);
            stars.add(s);
        }

        for (int i=0; i<3;i++){
            Enemy enemy=new Enemy(context,screenX,screenY);
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
        player.update();

        for (Star s:stars){
            s.update(player.getSpeed());
        }

        for (Enemy e:enemies){
            e.update(player.getSpeed());
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

package com.example.savaque.redcapkingdom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

    GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize gameView and set it as the view
        gameView = new GameView(this);
        setContentView(gameView);


    }

    // This method executes when the player starts the game
    @Override
    protected void onResume() {
        super.onResume();

        // Tell the gameView resume method to execute
        gameView.resume();
    }

    // This method executes when the player quits the game
    @Override
    protected void onPause() {
        super.onPause();

        // Tell the gameView pause method to execute
        gameView.pause();
    }
    class GameView extends SurfaceView implements Runnable {


        // Our Thread
        Thread gameThread = null;

        // Our GUI
        GUI gui= new GUI();

        //Will be used with Paint and Canvas
        SurfaceHolder ourHolder;

        //Is the game playing?
        volatile boolean playing;

        //Canvas and Paint objects
        Canvas canvas;
        Paint paint;

        float xPosition=0;
        float yPosition=0;

        //Tracks the game's Framerate
        int fps =30;

        Player player = new Player(fps);
        Tile wall = new Tile(900,500);

        public GameView(Context context) {
            super(context);
            //Initialize Holder and Paint objects
            ourHolder = getHolder();
            paint = new Paint();

        }
        public boolean onTouchEvent(MotionEvent motionEvent) {


            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {


                // Player has removed finger from screen
                case MotionEvent.ACTION_DOWN:

                    xPosition = motionEvent.getX();
                    yPosition = motionEvent.getY();
                    player.setDestination(xPosition,yPosition);
                    gui.active=true;
                    gui.setJoystickLoc(xPosition,yPosition);
                    break;
                case MotionEvent.ACTION_MOVE:
                    xPosition = motionEvent.getX();
                    yPosition = motionEvent.getY();
                    gui.setJoystickInput(xPosition,yPosition);
                    break;
                case MotionEvent.ACTION_UP:
                    gui.deactivate();
            }
            return true;
        }
        public void draw() {


            //Ensure the drawing surface exists

            if (ourHolder.getSurface().isValid()) {

                // Lock the canvas ready to draw
                // Make the drawing surface our canvas object

                canvas = ourHolder.lockCanvas();



                // Draw the background color
                canvas.drawColor(Color.argb(255, 26, 128, 182));

                // Choose the brush color for drawing
                paint.setColor(Color.argb(255, 249, 129, 0));

                canvas.drawCircle(player.location[0], player.location[1], 100, paint);
                //paint.setColor(Color.argb(0,0,0,0));

                //Lastly paint the GUI over everything
                if (gui.active){
                    gui.drawGUI(paint,canvas);
                }
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }



        public void run() {
            while (playing) {
                player.move(gui.joystickLoc,gui.joystickInput);
                draw();
            }
        }

        public void pause() {
            playing = false;
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                Log.e("Error:", "joining thread");
            }
        }

        // start our thread.
        public void resume() {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
    }
}

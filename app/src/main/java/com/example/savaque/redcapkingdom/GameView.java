package com.example.savaque.redcapkingdom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {
    private int SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().widthPixels;

    // Our Thread
    Thread gameThread = null;

    // Will be used with Paint and Canvas
    SurfaceHolder ourHolder;
    Canvas canvas;
    Paint paint;

    // FPS Counter
    long fpsTest = 0;
    long timeThisFrame = 1;

    // Is the game isPlaying?
    volatile boolean isPlaying;

    World world;
    Camera playerCamera;
    Player player;
    Level level;

    long previousMillis;
    long currentMillis;
    long frameTime;

    float[] joystick = new float[]{0,0};

    public GameView(Context context) {
        super(context);

        //Initialize Holder and Paint objects
        ourHolder = getHolder();
        paint = new Paint();

        // Create a new game world
        world = new World(context);

        // Get references to the current player and level
        player = world.createPlayer(joystick);
        level = world.loadLevel();

        // Create a playerCamera for panning across the level
        playerCamera = new Camera(joystick, player);

        previousMillis = System.currentTimeMillis();
        currentMillis = System.currentTimeMillis();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        playerCamera.onTouchEvent(motionEvent);
        return true;
    }

    public void update() {
        previousMillis = currentMillis;
        currentMillis = System.currentTimeMillis();
        frameTime = currentMillis - previousMillis;
        world.update(frameTime);
        playerCamera.update(frameTime);
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

            // Draw the current world on the canvas.
            world.draw(canvas, paint);

            // Shift the camera so we're looking at the player location in the level.
            playerCamera.draw(canvas, paint);

            // Display the current fps on the screen
            paint.setTextSize(45);
            canvas.drawText("FPS:" + fpsTest, 20, 40, paint);
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void run() {
        while (isPlaying) {
            update();
            draw();
            if (frameTime > 0) {
                fpsTest = (int) (1000 / frameTime);
            }
        }
    }

    public void pause() {
        isPlaying = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }
    }

    // start our thread.
    public void resume() {
        isPlaying = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
}
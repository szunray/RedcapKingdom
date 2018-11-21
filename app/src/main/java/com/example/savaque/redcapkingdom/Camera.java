package com.example.savaque.redcapkingdom;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

public class Camera {
    int half_SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().heightPixels / 2;
    int half_SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().widthPixels / 2;

    private float[] scale;
    private int zoomLevel;

    public float location[] = new float[2];
    public float velocity[] = new float[2];
    public float acceleration[] = new float[2];

    public float maxAccel = 0.002f;
    public float maxSpeed = 1.8f;
    public float friction = maxAccel / 2.0f;


    Player player;

    // Our GUI
    GUI gui;

    public Camera(float[] joystick, Player player) {
        scale = new float[]{2, 2};
        gui = new GUI(joystick);
        this.player = player;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        gui.handleTouch(motionEvent);
        return true;
    }

    public void update(float time) {
        // For simplicity, just set acceleration equal to max acceleration
        // in whichever direction the joystick is pointing.
        acceleration[0] = player.acceleration[0] / 2.0f;
        acceleration[1] = player.acceleration[1] / 2.0f;

        // If you aren't moving the joystick, set the acceleration to some small value
        // in the opposite direction of movement, so the player slowly slows down.
        if (acceleration[0] == 0) {
            if (friction * time > Math.abs(velocity[0])) {
                velocity[0] = 0;
            } else {
                if (velocity[0] > friction) {
                    acceleration[0] = -friction;
                } else if (velocity[0] < -1 * friction) {
                    acceleration[0] = friction;
                } else {
                    acceleration[0] = 0;
                    velocity[0] = 0;
                }
            }
        }

        // Velocity = (the change in time) * acceleration
        velocity[0] += acceleration[0] * time;

        if (Math.abs(velocity[0]) < friction) {
            velocity[0] = 0;
        }

        // Make sure the player isn't going faster than they're supposed to.
        if (velocity[0] > maxSpeed) {
            velocity[0] = maxSpeed;
        } else if (velocity[0] < -maxSpeed) {
            velocity[0] = -maxSpeed;
        }

        // Position = (the change in time) * velocity
        location[0] += velocity[0] * time;
        //World.offset[0] = location[0];
        location[1] += velocity[1] * time;
        //World.offset[1] = location[1];
    }


    public void draw(Canvas canvas, Paint paint) {
        gui.draw(canvas, paint);
        canvas.translate(-location[0] , -location[1]);
        Log.i("derps:", "" + location[0]);
        canvas.scale(scale[0], scale[1]);
    }
}

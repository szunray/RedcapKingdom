package com.example.savaque.redcapkingdom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

/**
 * Created by Savaque on 10/2/2018.
 */

public class Player {
    int half_SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().heightPixels / 2;
    int half_SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().widthPixels / 2;

    public float[] size = new float[]{100, 100};
    public float[] scale = new float[]{4, 4};

    public float location[] = new float[2];
    public float velocity[] = new float[2];
    public float acceleration[] = new float[2];
    public float joystick[];

    public float maxAccel = 0.002f;
    public float maxSpeed = 1.8f;
    public float friction = maxAccel / 2.0f;

    private Drawable sprite;

    public Player(Context context, int xPos, int yPos, float[] joystick) {
        // The phone is sideways yo
        location[0] = xPos;
        location[1] = yPos;

        // temporary. Just storing a reference to the gui joystick.
        this.joystick = joystick;

        sprite = context.getResources().getDrawable(R.drawable.spr_m_pjnerd2);
        sprite.setBounds(
                (int) (-size[0] * scale[0] / 2.0), // Left
                (int) (-size[1] * scale[1] / 2.0), // Top
                (int) (size[0] * scale[0] / 2.0), // Right
                (int) (size[1] * scale[1] / 2.0) // Bottom
        );
    }

    public void update(float time) {
        // For simplicity, just set acceleration equal to max acceleration
        // in whichever direction the joystick is pointing.
        acceleration[0] = joystick[0];
        if (acceleration[0] > maxAccel) {
            acceleration[0] = maxAccel;
        } else if (acceleration[0] < -1 * maxAccel) {
            acceleration[0] = -1 * maxAccel;
        }
        acceleration[1] = joystick[1];

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
        World.offset[0] = -location[0] + half_SCREEN_HEIGHT + 200 * velocity[0];
        location[1] += velocity[1] * time;
        World.offset[1] = -location[1] + half_SCREEN_WIDTH + 200 * velocity[1];
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(World.offset[0] + location[0], World.offset[1] + location[1]);
        sprite.draw(canvas);
        canvas.restore();
    }
}

package com.example.savaque.redcapkingdom;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class Camera {
    private float[] scale;
    private float[] position;

    private int xVel;
    private int yVel;
    private int xAccel;
    private int yAccel;
    private int zoomLevel;

    // Our GUI
    GUI gui ;

    public Camera(float[] joystick) {
        scale = new float[]{1,1};
        position = new float[]{0,0};
        gui = new GUI(joystick);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        gui.handleTouch(motionEvent);
        return true;
    }

    public void draw(Canvas canvas, Paint paint){
        canvas.translate(position[0], position[1]);
        canvas.scale(scale[0], scale[1]);
    }
}

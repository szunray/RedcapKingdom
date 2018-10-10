package com.example.savaque.redcapkingdom;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

/**
 * Created by Savaque on 10/6/2018.
 */

public class GUI {
    float[] joystickLoc = new float[2];
    float[] joystickInput = new float[2];
    int screenWidth= Resources.getSystem().getDisplayMetrics().widthPixels;
    int screenHeight= Resources.getSystem().getDisplayMetrics().heightPixels;;
    boolean active;

    public GUI(){
        joystickLoc[0]=0;
        joystickLoc[1]=0;
        joystickInput[0]= 0;
        joystickInput[1]= 0;
        active=true;
    }

    public void setJoystickLoc(float x, float y){
        joystickLoc[0]= x;
        joystickLoc[1]= y;
    }
    public void setJoystickInput(float x, float y){
        joystickInput[0]= x;
        joystickInput[1]= y;
    }
    public void deactivate(){
        joystickInput[0]=joystickLoc[0];
        joystickInput[1]=joystickLoc[1];
        active=false;
    }
    public void drawGUI(Paint paint, Canvas canvas){
        canvas.drawCircle(screenWidth-200, screenHeight/2, 100, paint);
        canvas.drawCircle(screenWidth-400, screenHeight/2+200, 100, paint);
        if (active) {
            paint.setColor(Color.argb(255, 9, 129, 0));
            canvas.drawCircle(joystickLoc[0], joystickLoc[1], 100, paint);
            paint.setColor(Color.argb(255, 249, 19, 0));
            canvas.drawCircle(joystickInput[0], joystickInput[1], 90, paint);
            paint.setColor(Color.argb(255, 249, 129, 0));
        }
    }
//TODO: This just needs work in General.
    public void handleTouch(MotionEvent motionEvent) {

        float xPosition = motionEvent.getX();
        float yPosition = motionEvent.getY();

        if (inRadius (xPosition,yPosition,screenWidth-200,screenHeight/2,200)){
            return;
        }
        else {
            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    active = true;
                    setJoystickLoc(xPosition, yPosition);
                    break;
                case MotionEvent.ACTION_MOVE:
                    setJoystickInput(xPosition, yPosition);
                    break;
                case MotionEvent.ACTION_UP:
                    deactivate();
            }
        }
    }

    public boolean inRadius(float inputX, float inputY, float targetX,float targetY, int radius){
        double distance = Math.pow((inputX-targetX),2) + Math.pow((inputY-targetY),2);
        if (distance < Math.pow(radius,2))
            return true;
        else
            return false;
    }
}

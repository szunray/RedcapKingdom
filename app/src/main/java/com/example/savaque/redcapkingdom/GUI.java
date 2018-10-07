package com.example.savaque.redcapkingdom;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Savaque on 10/6/2018.
 */

public class GUI {
    float[] joystickLoc = new float[2];
    float[] joystickInput = new float[2];
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
        paint.setColor(Color.argb(255, 9, 129, 0));
        canvas.drawCircle(joystickLoc[0], joystickLoc[1], 100, paint);
        paint.setColor(Color.argb(255, 249, 19, 0));
        canvas.drawCircle(joystickInput[0], joystickInput[1], 90, paint);
        paint.setColor(Color.argb(255, 249, 129, 0));
    }
}

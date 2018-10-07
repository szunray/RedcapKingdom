package com.example.savaque.redcapkingdom;

/**
 * Created by Savaque on 10/2/2018.
 */

public class Player {
    public float location[] = new float[2];
    public float destination[] = new float [2];
    public int fps;

    public float speed;

    public Player(int fpsValue){
        location[0]=0;
        location[1]=0;
        destination[0]=0;
        destination[1]=0;
        fps = fpsValue;

        speed = 400;
    }

    public void setDestination(float x,float y){
        destination[0]=x;
        destination[1]=y;
    }
    public void move(){
        float slopeX = Math.abs((location[1]-destination[1])/(location[0]-destination[0]));
        float slopeY = Math.abs((location[0]-destination[0])/(location[1]-destination[1]));
        if (slopeX<1){
            slopeX = 1;
        }
        if (slopeY<1){
            slopeY = 1;
        }
        float margin = 40;
        if (destination[0]>location[0]+margin)
        {
            location[0] = location[0] + (speed / fps)/slopeX;
        }
        else if (destination[0]<location[0]-margin)
        {
            location[0] = location[0] - (speed / fps)/slopeX;
        }
        if (destination[1]>location[1]+margin)
        {
            location[1] = location[1] + (speed / fps)/slopeY;
        }
        else if (destination[1]<location[1]-margin)
        {
            location[1] = location[1] - (speed / fps)/slopeY;
        }
    }

    public void move(float[] joystickLoc, float[] thumbLoc){
        if (thumbLoc[0]>joystickLoc[0]){
            location[0] += (speed / fps);
        }
        else if (thumbLoc[0]<joystickLoc[0]){
            location[0] -= (speed / fps);
        }

        if (thumbLoc[1]>joystickLoc[1]){
            location[1] += (speed / fps);
        }
        else if (thumbLoc[1]<joystickLoc[1]){
            location[1] -= (speed / fps);
        }
    }
}

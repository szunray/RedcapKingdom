package com.example.savaque.redcapkingdom;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Savaque on 10/2/2018.
 */

public class Tile {
    float location[];
    float width;
    float height;
    float left, right, top, bottom;

    Bitmap image;

    public static final float StandardSize = 250;

    public Tile(Bitmap b){
        image = b;
        width = 250;
        height = 250;
    }

    public Tile(float x, float y) {
        location = new float[2];
        location[0] = x;
        location[1] = y;
        width = 500;
        height = 100;
        left = location[0] - width / 2;
        right = location[0] + width / 2;
        top = location[1] + height / 2;
        bottom = location[1] - height / 2;
        height = 100;

    }
}


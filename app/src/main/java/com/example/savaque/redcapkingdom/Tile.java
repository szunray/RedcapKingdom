package com.example.savaque.redcapkingdom;

/**
 * Created by Savaque on 10/2/2018.
 */

public class Tile {
    float location[];
    float width;
    float height;
    float left, right, top, bottom;

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


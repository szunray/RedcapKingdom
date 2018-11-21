package com.example.savaque.redcapkingdom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Hard code ALLTHETHINGS
public class Level {
    int SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().heightPixels;
    int SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().widthPixels;

    Tile[][] tileMap;
    Bitmap test;

    Bitmap background;


    public Level(Context context) {
        Bitmap temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.glacial_mountains);
        background = Bitmap.createScaledBitmap(temp, 5000, 2500, true);

        temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.ground01);
        Bitmap tile = Bitmap.createScaledBitmap(temp, 250, 250, true);

        tileMap = new Tile[][]{
                {new Tile(tile), null, null, null, null},
                {new Tile(tile), null, new Tile(tile), null, null},
                {new Tile(tile), null, null, null, null},
                {new Tile(tile), null, null, null, null},
                {new Tile(tile), null, null, null, null},
                {new Tile(tile), new Tile(tile), null, null, null},
                {new Tile(tile), null, null, null, null},
                {new Tile(tile), null, null, null, null},
                {new Tile(tile), null, null, null, null},
                {new Tile(tile), null, null, null, null},
        };

    }

    public void update(float time) {

    }

    public void draw(Canvas canvas, Paint paint) {

        canvas.drawBitmap(background,
                (float) (0),
                (float) (0),
                paint);

        for (int x = 0; x < tileMap.length; x++) {
            for (int y = 0; y < tileMap[x].length; y++) {
                if (tileMap[x][y] != null) {
                    canvas.save();
                    canvas.translate(x * Tile.StandardSize, SCREEN_WIDTH - (y + 1) * Tile.StandardSize);
                    canvas.drawBitmap(tileMap[x][y].image,
                            (float) (0),
                            (float) (0),
                            paint);
                    canvas.restore();

                }
            }
        }
    }
}

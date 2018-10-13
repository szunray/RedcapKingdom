package com.example.savaque.redcapkingdom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Hard code ALLTHETHINGS
public class Level {

    private List<Material> groundTiles;
    private Material[][] tileMap;
    private Drawable background;

    public Level(Context context) {
        groundTiles = new ArrayList<>();
        groundTiles.add(new Material(context.getResources().getDrawable(R.drawable.ground01)));
        groundTiles.add(new Material(context.getResources().getDrawable(R.drawable.ground02)));
        groundTiles.add(new Material(context.getResources().getDrawable(R.drawable.ground03)));
        groundTiles.add(new Material(context.getResources().getDrawable(R.drawable.ground04)));
        groundTiles.add(new Material(context.getResources().getDrawable(R.drawable.ground05)));
        groundTiles.add(new Material(context.getResources().getDrawable(R.drawable.ground06)));
        groundTiles.add(new Material(context.getResources().getDrawable(R.drawable.ground07)));
        groundTiles.add(new Material(context.getResources().getDrawable(R.drawable.ground08)));

        tileMap = new Material[][]{
                {randomGroundTile(), null, null, null, null, null},
                {randomGroundTile(), null, null, randomGroundTile(), null, null},
                {randomGroundTile(), null, null, null, null, null},
                {randomGroundTile(), null, null, null, null, null},
                {randomGroundTile(), randomGroundTile(), null, null, null, null},
                {null, null, null, null, randomGroundTile(), null},
                {null, null, null, null, randomGroundTile(), null},
                {randomGroundTile(), null, null, null, null, null},
                {randomGroundTile(), null, null, randomGroundTile(), null, null},
                {randomGroundTile(), null, null, null, null, null},
                {randomGroundTile(), null, null, null, randomGroundTile(), null},
                {randomGroundTile(), randomGroundTile(), null, null, null, null},
                {null, null, null, null, randomGroundTile(), null},
                {null, null, null, null, randomGroundTile(), null},
        };

        background = context.getResources().getDrawable(R.drawable.glacial_mountains);
        background.setBounds(0, 0, 384*6, 216*6);

    }

    public void draw(Canvas canvas) {

        int SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().heightPixels;
        int SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().widthPixels;

        background.draw(canvas);
        double xScale = 200.0;
        double yScale = 200.0;
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {
                if (tileMap[i][j] != null) {
                    tileMap[i][j].drawable.setBounds((int)(i*xScale), SCREEN_WIDTH -(int)yScale - (int)(j*yScale), (int)((i+1)*xScale), SCREEN_WIDTH -(int)yScale -(int)((j-1)*yScale));
                    tileMap[i][j].drawable.draw(canvas);
                }
            }
        }
    }

    private Material randomGroundTile() {
        return groundTiles.get(new Random().nextInt(groundTiles.size()));
    }

    public class Material {
        Drawable drawable;

        public Material(Drawable d) {
            this.drawable = d;
        }
        // Holds the information about a given material.
        // These are interactable and on the same plane as the player.
    }
}

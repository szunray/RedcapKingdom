package com.example.savaque.redcapkingdom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

public class World {
    int SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().heightPixels;
    int SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().widthPixels;

    public static float[] offset = new float[]{0,0};

    Context context;

    Level level;
    Player player;

    public World(Context context) {
        this.context = context;
    }

    // Figure out the best way to add multiple players
    public Player createPlayer(float[] joystick) {
        player = new Player(context, SCREEN_HEIGHT/2, SCREEN_WIDTH / 2, joystick);
        return player;
    }

    // Support loading in different levels by name
    public Level loadLevel() {
        this.level = new Level(context);
        return level;
    }

    public void update(float time) {
        level.update(time);
        player.update(time);
    }

    public void draw(Canvas canvas, Paint paint){
        level.draw(canvas, paint);
        player.draw(canvas, paint);
    }
}

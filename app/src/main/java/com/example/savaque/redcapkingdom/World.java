package com.example.savaque.redcapkingdom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

public class World {
    Context context;

    Level level;
    Player player;

    public World(Context context) {
        this.context = context;
    }

    // Figure out the best way to add multiple players
    public Player createPlayer(float[] joystick) {
        player = new Player(context, 200, 400, joystick);
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

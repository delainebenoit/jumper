package fr.iutlens.mmi.jumper;

import android.graphics.Canvas;

import fr.iutlens.mmi.jumper.utils.SpriteSheet;

class Fond {

    private  final SpriteSheet sprite;

    public Fond(int sprite_id) {
        sprite =  SpriteSheet.get(sprite_id);
    }

    public void paint(Canvas canvas, float height) {
        canvas.save();
        float scale = height/sprite.h;
        canvas.scale(scale, scale);

        sprite.paint(canvas,0,0,0);
        sprite.paint(canvas,1,sprite.w,0);
        canvas.restore();
    }




}

package fr.iutlens.mmi.spacejump;

import android.graphics.Canvas;

import fr.iutlens.mmi.spacejump.utils.SpriteSheet;

/**
 * Created by mehdi on 16/03/2018.
 */

class Fond {

    private final SpriteSheet sprite;

    public Fond(int sprite_id) {
        sprite = SpriteSheet.get(sprite_id);
    }

    public void paint(Canvas canvas, float height, float d) {
        canvas.save();

        float scale = height / sprite.h;

        canvas.scale(scale, scale);

        /*sprite.paint(canvas, 0, 0, 0);
        sprite.paint(canvas, 1, sprite.w, 0);*/

        d = d - ((int) d);
        int ndx = (int) (d * 2);
        int ndx2 = 1 - ndx;
        float offs = - (2 * d - ndx) * sprite.w;

        sprite.paint(canvas, ndx, offs, 0);
        sprite.paint(canvas, ndx2, sprite.w + offs, 0);
        sprite.paint(canvas, ndx, sprite.w * 2 + offs, 0);
        sprite.paint(canvas, ndx2, sprite.w * 3 + offs, 0);

        canvas.restore();
    }

}

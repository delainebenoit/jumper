package fr.iutlens.mmi.jumper;

import android.graphics.Canvas;
import android.util.Log;

import fr.iutlens.mmi.jumper.utils.SpriteSheet;

/**
 * Created by dubois on 30/12/2017.
 */

public class Hero {

    public static final int SAME_FRAME = 3;
    private final float BASELINE = 0.93f;


    public static final float MAX_STRENGTH = 2f;
    private final float G = 0.2f;
    private final float IMPULSE = 2.5f;

    private SpriteSheet sprite;

    private float y;
    private float vy;
    private float vx;

    private float jump;

    private int frame;
    private int cpt;
    private int nbjump;
    public boolean hit;


    public Hero(int sprite_id, float vx){
        sprite = SpriteSheet.get(sprite_id);
        y = 0;
        vy = 0;
        jump = 0;
        frame =0;
        cpt = 0;

        this.vx = vx;
    }


    public float getY(){
        return y;
    }

    public void update(float floor, float slope){
        hit = false;
        y += vy; // inertie
        float altitude = y-floor;
        if (altitude <0){ // On est dans le sol : atterrissage
            float a = altitude-vy+slope*vx;
            if (a <-0.5f && altitude<-0.5f) {
                hit = true;
                Log.d("update","planté");
            }
            vy = 0; //floor-y;
            y = floor;
            altitude = 0;
        }
        if (altitude == 0){ // en contact avec le sol
            nbjump = 0;
            if (jump != 0) {
                vy = jump*IMPULSE*vx; // On saute ?
                frame = 3;
                nbjump =   nbjump + 1;
            } else {
//                vy = -G*vx;
                vy = (slope-G)*vx; // On suit le sol...
                cpt = (cpt+1)% SAME_FRAME;
                if (cpt==0) frame = (frame+1)%8;
            }
        } else { // actuellement en vol
            if ( jump != 0 && nbjump<2) {
                vy = jump * IMPULSE * vx; // On saute ?
                frame = 3;
                nbjump = nbjump + 1;
            }

            vy -= G*vx; // effet de la gravité
            frame = (vy>0) ? 3 : 5;
//            if (y < floor+slope*vx) y = floor+slope*vx; // atterrissage ?
        }

        jump = 0;
    }

    public void paint(Canvas canvas, float x, float y){
        sprite.paint(canvas,frame,x-sprite.w/2,y-sprite.h*BASELINE);
    }

    public void jump(float strength) {
        if (strength>MAX_STRENGTH) strength = MAX_STRENGTH;
        if (strength> jump) jump = strength;
    }
}

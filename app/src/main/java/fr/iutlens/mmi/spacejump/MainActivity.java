package fr.iutlens.mmi.spacejump;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import fr.iutlens.mmi.spacejump.utils.AccelerationProxy;

/***
 * Crédits image : https://pixabay.com/fr/lapin-dessin-animé-jeu-élément-1582176/
 *
 *
 *
 */
public class MainActivity extends AppCompatActivity {

    private AccelerationProxy proxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.activity_main);
        // On récupère la vue du jeu
        GameView game = findViewById(R.id.gameView);

        // On configure le jeu pour recevoir les changements d'orientation
        proxy = new AccelerationProxy(this, game);
        TextView score = findViewById(R.id.textViewScore);
        game.setTextViewScore(score);
    }


    @Override
    protected void onResume() {
        super.onResume();
        proxy.resume(); // On relance l'accéléromètre
    }

    @Override
    protected void onPause() {
        super.onPause();
        proxy.pause(); // On mets en pause l'accéléromètre
    }
}

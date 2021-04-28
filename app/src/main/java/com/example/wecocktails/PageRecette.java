package com.example.wecocktails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PageRecette extends AppCompatActivity {

    TextView fenetre_resultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_recette2);

        Intent intent = getIntent();
        if (intent != null) {


            String cocktailChoisi = "";
            if (intent.hasExtra("cocktailChoisi")) { // vérifie qu'une valeur est associée à la clé “alcoolChoisi”
                cocktailChoisi = intent.getStringExtra("cocktailChoisi"); // on récupère la valeur associée à la clé dans une variable
            }
            fenetre_resultat = findViewById(R.id.resultat);
            fenetre_resultat.setText(cocktailChoisi); //affichage de cette variable dans fenêtre résultat
        }
    }
}
package com.example.wecocktails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class MainActivity<lateinit, adapter> extends AppCompatActivity {

    Button boutonDemarrer;
    //On déclare le premier bouton qui permet de débuter sur l'appliction




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        boutonDemarrer = (Button) findViewById(R.id.button);

        boutonDemarrer.setOnClickListener(new View.OnClickListener() {
            //On place un écouteur sur le bouton pour savoir quand est ce qu'on aura cliquer dessus
            @Override
            public void onClick(View view) {
                //Lorsqu'on clique on effectue les actions suivantes :
                //Ouverture de l'activité Page Select, qui corresponds au choix de l'alcool principal du cocktail.
                Intent intent = new Intent(MainActivity.this, PageSelect.class);
                startActivity(intent);

            }
        });



        }

    }

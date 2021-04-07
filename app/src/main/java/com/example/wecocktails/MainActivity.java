package com.example.wecocktails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class MainActivity<lateinit, adapter> extends AppCompatActivity {

    Button boutonDemarrer;
    //private Button play;

    private lateinit var adapter : ArrayAdapter<*>


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        boutonDemarrer = (Button) findViewById(R.id.button);

        boutonDemarrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ovuerture de l'activité Page Recherche
                Intent intent = new Intent(MainActivity.this, PageRecherche.class);
                startActivity(intent);

            }
        });


        }

    }

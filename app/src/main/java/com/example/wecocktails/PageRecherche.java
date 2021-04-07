package com.example.wecocktails;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class PageRecherche extends AppCompatActivity {

    private ArrayAdapter adapter ;
    ListView lv_listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_recherche);

        adapter = new ArrayAdapter( this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ListCocktails));
        lv_listView = (ListView) findViewById(R.id.lv_listView);
        lv_listView.setAdapter(adapter);

        lv_listView.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String pokemonChoisi = parent.getSelectedItem().toString();
                // on affiche le contenu de la variable dans la zone de texte resultat
                //fenetre_resultat.setText(pokemonChoisi);

                };



            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });



    }
}
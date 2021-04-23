package com.example.wecocktails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class PageSelect extends AppCompatActivity {
    Spinner spinner_alcool;
    TextView fenetre_resultat;
    SQLiteDatabase maBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_select);


        try {
            maBase = openOrCreateDatabase("maBaseDeDonneesAlcool", MODE_PRIVATE, null);

            maBase.execSQL("CREATE TABLE IF NOT EXISTS alcool (" +
                    " alcool_principal PRIMARY KEY);"
            );
            // on la vide (sinon on recréerait a chaque fois les pokemon a chaque nouveau lancement)
            maBase.execSQL(" delete from alcool where 1;");
            // on la remplit de quelques elements  la table pokemon
            maBase.execSQL("insert into alcool (alcool_principal) values ('Vodka');");
            maBase.execSQL("insert into alcool (alcool_principal) values ('Vodka');");
            maBase.execSQL("insert into alcool (alcool_principal) values ('Rhum');");
            maBase.execSQL("insert into alcool (alcool_principal) values ('Gin');");
            maBase.execSQL("insert into alcool (alcool_principal) values ('Vodka');");
            maBase.execSQL("insert into alcool (alcool_principal) values ('Rhum');");
            maBase.execSQL("insert into alcool (alcool_principal) values ('Rhum');");
            maBase.execSQL("insert into alcool (alcool_principal) values ('Rhum');");
            maBase.execSQL("insert into alcool (alcool_principal) values ('Vodka');");
            maBase.execSQL("insert into alcool (alcool_principal) values ('Vin blanc ');");
            maBase.execSQL("insert into alcool (alcool_principal) values ('Tequila');");
            maBase.execSQL("insert into alcool (alcool_principal) values ('Rhum');");
        } catch (SQLException e) {
            // s'il y a eu un probleme lors de l'exécution de la requete, on le capture
            Log.e("execSQL", "Erreur SQL : " + e.getMessage());
        }
        // on associe ensuitre les références objets  aux éléments de l'activité
        spinner_alcool  = findViewById(R.id.spinner_ckt);
        fenetre_resultat  = findViewById(R.id.resultat);

        // on crée un tableau de string appelé results qui va contenir les alcools de la base que l'on veut dans le spinner
        // par exemple on ne va afficher que les pokemon Dragon
        final ArrayList<String> results = new ArrayList<String>();
        try {
            // on execute la requete SQL et on récupère les résultats dans un Cursor c
            Cursor c = maBase.rawQuery("Select alcool_principal from alcool order by alcool_principal asc;", null);
            // on ajoute chaque ligne du cursor dans le tableau results
            while (c.moveToNext()) {
                String a = c.getString(c.getColumnIndex("nom"));
                results.add(a);
                System.out.println(a+"debug");
            }
        }
        catch (SQLiteException se ) {
            Log.e("rawQuery", "Probleme SQL");
        }

        // On cree un ArrayAdapter à partir de results et on sélectionne la mise en forme par defaut
        ArrayAdapter monAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, results);


        // on lie enfin le spinner avec l'adapteur créé
        spinner_alcool.setAdapter(monAdapter);

        // On définit enfin ce qu'on fait quand on selectionne un pokemon du menu deroulant
        spinner_alcool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On récupère le pokemon choisi dans une variable
                String cocktailChoisi = parent.getSelectedItem().toString();
                //Intent intent = new Intent(PageSelect.this, PageRecherche.class);
                //startActivity(intent);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

    }
}

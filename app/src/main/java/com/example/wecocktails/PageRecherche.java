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
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class PageRecherche extends AppCompatActivity {
    ListView list_cocktail;
    TextView fenetre_resultat;
    SQLiteDatabase maBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_recherche);
        Intent intent = getIntent();

        if (intent != null) {


            String alcoolChoisi = "";
           if (intent.hasExtra("alcoolChoisi")){ // vérifie qu'une valeur est associée à la clé “alcoolChoisi”
                alcoolChoisi = intent.getStringExtra("alcoolChoisi"); // on récupère la valeur associée à la clé dans une variable
           }
            //On affiche l'alcool selectionné dans la page précédente
            fenetre_resultat = findViewById(R.id.resultat);
            fenetre_resultat.setText(alcoolChoisi); //affichage de cette variable dans fenêtre résultat
            System.out.println(fenetre_resultat.getText());


            try { //création de la base de données des cocktails
                maBase = openOrCreateDatabase("maBaseDeDonneesCocktails", MODE_PRIVATE, null);

                maBase.execSQL("CREATE TABLE IF NOT EXISTS cocktail(" +
                        " nom text PRIMARY KEY," + //clé primaire étant le nom
                        " alcool_principal text NOT NULL," +
                        " ingrediants text );"
                );
                // on la vide
                maBase.execSQL(" delete from cocktail where 1;");
                // on la remplit
                maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Bloody Mary', 'Vodka','4cl Vodka, 12cl jus de tomates, 0,5cl citron, 0,5clworcestershire sauce, tabasco');");
                maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Blue Lagoon', 'Vodka', '4cl Vodka, 3cl Curaçao, 2cl citron, glaçons ');");
                maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Daiquiri', 'Rhum', '4cl Rhum, 2cl jus de citron vert, 1cl sirop sucre de canne ');");
                maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Gin Tonic', 'Gin', '4cl de Gin, 8cl de tonic, 2 feuilles de menthe , rondelle citron vert, glaçon ');");
                maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Long island ', 'Vodka', '1,5cl de vokda, 1,5cl de gin, 1,5cl de rhum blanc, 1,5cl de triple sec, 1,5cl de tequila, 1cl de jus de citron, 3cl de coca');");
                maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Mai Thai', 'Rhum', '3cl de rhum ambré, 3cl de rhum blanc, 2cl de triple sec, 1cl de sucre de canne, 3cl de jus de citron vert, 3cl de sirop d orgeat');");
                maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Mojito', 'Rhum', '4cl de rhum blanc, eau gazeuse, 2cl de sirop sucre de canne, 1/2 citron vert, 6 feuilles de menthe, 5 glaçons ');");
                maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Pina Colada', 'Rhum','4cl Rhum Blanc, 4cl rhum ambré, 12cl jus d ananas, 4cl lait de coco');");
                maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Sex on the beach ', 'Vodka', '3cl Vodka, 3cl Liqueur de peche, 6cl jus ananas, 10cl jus de cramberry  ');");
                maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Spritz', 'Vin blanc', '6cl Vin blanc petillant, 4cl Aperol, 2cl eau gazeuse, rondelle orange, glaçons  ');");
                maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Tequila Sunrise ', 'Tequila', '6cl Tequila, 12cl Jus orange, 2cl grenadine, glaçons');");
                maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Tit Punch ', 'Rhum', '5cl Rhum blanc , 2cl sirop sucre de canne , quartier citron vert  ');");
            } catch (SQLException e) {
                // s'il y a eu un probleme lors de l'exécution de la requete, on le capture
                Log.e("execSQL", "Erreur SQL : " + e.getMessage());
            }

            list_cocktail = findViewById(R.id.scroll_ckt);


            // on crée un tableau de string appelé results qui va contenir les cocktails de la base que l'on veut dans le spinner
            // on sépare en fonction des 5 alcools principaux : 5 requêtes différentes
            final ArrayList<String> results = new ArrayList<String>();
            try {
                // on execute la requete SQL et on récupère les résultats dans un Cursor c
                if (fenetre_resultat.getText().toString().equals("Vodka")) {
                    //la première requete verifie si l'alcool choisi est la vokda et affiche les cocktails ayant comme alcool principal la vodka
                    Cursor c = maBase.rawQuery("Select nom from cocktail WHERE alcool_principal='Vodka' order by nom asc;", null);
                    // on ajoute chaque ligne du cursor dans le tableau results
                    while (c.moveToNext()) {
                        String a = c.getString(c.getColumnIndex("nom"));
                        results.add(a);
                        //System.out.println(a + "debug");
                    }
               } else if (fenetre_resultat.getText().toString().equals( "Rhum")) {
                    Cursor c = maBase.rawQuery("Select nom from cocktail WHERE alcool_principal='Rhum' order by nom asc;", null);
                    while (c.moveToNext()) {
                        String a = c.getString(c.getColumnIndex("nom"));
                        results.add(a);
                    }
               } else if (fenetre_resultat.getText().toString().equals( "Vin blanc")) {
                    Cursor c = maBase.rawQuery("Select nom from cocktail WHERE alcool_principal='Vin blanc' order by nom asc;", null);
                    while (c.moveToNext()) {
                        String a = c.getString(c.getColumnIndex("nom"));
                        results.add(a);
                    }
                } else if (fenetre_resultat.getText().toString().equals( "Tequila")) {
                    Cursor c = maBase.rawQuery("Select nom from cocktail WHERE alcool_principal='Tequila' order by nom asc;", null);
                    while (c.moveToNext()) {
                        String a = c.getString(c.getColumnIndex("nom"));
                        results.add(a);
                    }
                } else if (fenetre_resultat.getText().toString().equals( "Gin")) {
                    Cursor c = maBase.rawQuery("Select nom from cocktail WHERE alcool_principal='Gin' order by nom asc;", null);
                    while (c.moveToNext()) {
                        String a = c.getString(c.getColumnIndex("nom"));
                        results.add(a);
                    }
                }
            } catch (SQLiteException se) {
                Log.e("rawQuery", "Probleme SQL");
            }

            // On cree un ArrayAdapter à partir de results et on sélectionne la mise en forme avec des items cliquables
            ArrayAdapter monAdapter1 = new ArrayAdapter(this, android.R.layout.simple_selectable_list_item, results);

            list_cocktail.setAdapter(monAdapter1);

            // On définit enfin ce qu'on fait quand on selectionne un cocktail du menu deroulant
            list_cocktail.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView,
                                        View view, int position, long rowId) {
                    // On récupère le nom du cocktail choisi dans une variable
                    String cocktailChoisi = list_cocktail.getSelectedItem().toString() ;
                    //pour par la suite pouvoir afficher sa recette entière.

                    //On récupère dans une variable l'alcool choisi afin de garder cette variable dans l'activité suivante
                    Intent intent = new Intent(PageRecherche.this, PageRecette.class);
                    intent.putExtra("cocktailChoisi", cocktailChoisi); //Put extra permets de passer cette variable dans la prochaine activité
                    startActivity(intent);

                }



            });

        }
    }
}

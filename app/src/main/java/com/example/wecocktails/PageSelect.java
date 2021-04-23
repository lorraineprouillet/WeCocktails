package com.example.wecocktails;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;

public class PageSelect extends AppCompatActivity {
    Spinner spinner_cocktail;
    TextView fenetre_resultat;
    SQLiteDatabase maBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_select);


        try {
            maBase = openOrCreateDatabase("maBaseDeDonneesCocktails", MODE_PRIVATE, null);

            maBase.execSQL("CREATE TABLE IF NOT EXISTS cocktail(" +
                    " nom text PRIMARY KEY," +
                    " alcool_principal text NOT NULL," +
                    " ingrediants text );"
            );
            // on la vide (sinon on recréerait a chaque fois les pokemon a chaque nouveau lancement)
            maBase.execSQL(" delete from cocktail where 1;");
            // on la remplit de quelques elements  la table pokemon
            maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Bloody Mary', 'Vodka','4cl Vodka, 12cl jus de tomates, 0,5cl citron, 0,5clworcestershire sauce, tabasco');");
            maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Blue Lagoon', 'Vodka', '4cl Vodka, 3cl Curaçao, 2cl citron, glaçons ');");
            maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Daiquiri', 'Rhum', '4cl Rhum, 2cl jus de citron vert, 1cl sirop sucre de canne ');");
            maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Gin Tonic', 'Gin', '4cl de Gin, 8cl de tonic, 2 feuilles de menthe , rondelle citron vert, glaçon ');");
            maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Log island ', 'Vodka', '1,5cl de vokda, 1,5cl de gin, 1,5cl de rhum blanc, 1,5cl de triple sec, 1,5cl de tequila, 1cl de jus de citron, 3cl de coca');");
            maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Mai Thai', 'Rhum', '3cl de rhum ambré, 3cl de rhum blanc, 2cl de triple sec, 1cl de sucre de canne, 3cl de jus de citron vert, 3cl de sirop d orgeat');");
            maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Mojito', 'Rhum', '4cl de rhum blanc, eau gazeuse, 2cl de sirop sucre de canne, 1/2 citron vert, 6 feuilles de menthe, 5 glaçons ');");
            maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Pina Colada', 'Rhum','4cl Rhum Blanc, 4cl rhum ambré, 12cl jus d ananas, 4cl lait de coco');");
            maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Sex on the beach ', 'Vodka', '3cl Vodka, 3cl Liqueur de peche, 6cl jus ananas, 10cl jus de cramberry  ');");
            maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Spritz', 'Vin blanc ', '6cl Vin blanc petillant, 4cl Aperol, 2cl eau gazeuse, rondelle orange, glaçons  ');");
            maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Tequila Sunrise ', 'Tequila', '6cl Tequila, 12cl Jus orange, 2cl grenadine, glaçons');");
            maBase.execSQL("insert into cocktail (nom, alcool_principal,ingrediants ) values ('Tit Punch ', 'Rhum', '5cl Rhum blanc , 2cl sirop sucre de canne , quartier citron vert  ');");
        } catch (SQLException e) {
            // s'il y a eu un probleme lors de l'exécution de la requete, on le capture
            Log.e("execSQL", "Erreur SQL : " + e.getMessage());
        }
        // on associe ensuitre les références objets  aux éléments de l'activité
        spinner_cocktail  = findViewById(R.id.spinner_pkm);
        fenetre_resultat  = findViewById(R.id.resultat);


    }
}

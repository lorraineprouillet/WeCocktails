package com.example.wecocktails;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

public class PageRecherche extends AppCompatActivity {

    private ArrayAdapter adapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_recherche);

        adapter = ArrayAdapter( this,android.R.layout.simple_list_item_1, resources.GetStringArray(R.array.ListCocktails));



    }
}
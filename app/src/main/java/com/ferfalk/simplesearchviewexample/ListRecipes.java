package com.ferfalk.simplesearchviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListRecipes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipes);

        ListView listView = findViewById(R.id.listView);

        List<String> al  = new ArrayList<>();
        al.add("Electronics");
        al.add("Ex Tc");
        al.add("computer engineering");
        al.add("mechanical");
        al.add("Electrical");
        al.add("Electronics");
        al.add("Ex Tc");
        al.add("computer engineering");
        al.add("mechanical");
        al.add("Electrical");
        al.add("Electronics");
        al.add("Ex Tc");
        al.add("computer engineering");
        al.add("mechanical");
        al.add("Electrical");

        ArrayAdapter<String> ad = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                al
        );

        listView.setAdapter(ad);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListRecipes.this, "Selected"+al.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
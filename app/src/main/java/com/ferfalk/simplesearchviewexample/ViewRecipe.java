package com.ferfalk.simplesearchviewexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewRecipe extends AppCompatActivity {
    Bundle extras;
    Bundle intent;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        intent = getIntent().getExtras();
        HashMap<String,String> rec = (HashMap<String, String>) intent.get("hashMap");
        String viewval = intent.getString("recipe");
        //text = findViewById(R.id.recipe);
        //text.setText(rec.get(viewval));
        Log.d("rajj",rec.get(viewval));

        ListView listView = findViewById(R.id.viewRecipe);

        List<String> al  = new ArrayList<>();
        String arr[] = rec.get(viewval).split(",");
        for(int i=4;i<arr.length;i++){
            al.add(arr[i]);
        }

        ArrayAdapter<String> ad = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                al
        );

        listView.setAdapter(ad);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ViewRecipe.this, "Selected"+al.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
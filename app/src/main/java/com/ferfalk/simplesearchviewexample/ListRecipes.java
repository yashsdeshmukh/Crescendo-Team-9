package com.ferfalk.simplesearchviewexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ListRecipes extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    List<String> al;
    ArrayAdapter<String> ad;
    HashMap<String,String> hashMap;
    ArrayList<String> ingrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipes);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = findViewById(R.id.listView);
        al  = new ArrayList<>();
        hashMap = new HashMap<>();
        myRef = database.getReference("Recipes");

        //myRef.addListenerForSingleValueEvent(valueEventListener);
        myRef.addListenerForSingleValueEvent(valueEventListener);

         ad = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                al
        );

        listView.setAdapter(ad);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListRecipes.this, "Selected"+al.get(position), Toast.LENGTH_SHORT).show();
                Log.d("raj",String.valueOf(parent.getItemAtPosition(position)));
                String viewRecipe = (String.valueOf(parent.getItemAtPosition(position)));
                Intent intent = new Intent(ListRecipes.this, ViewRecipe.class);
                intent.putExtra("recipe",viewRecipe);
                intent.putExtra("hashMap",hashMap);
                startActivity(intent);
            }
        });
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            //Log.d("War","size");
            if(snapshot.exists()){
                Log.d("snap","ss " +snapshot);

                List<DataSnapshot> list = new ArrayList<>();
                Bundle bundle = getIntent().getExtras();
                HashMap<Integer,ArrayList<String>> hashMap2 = (HashMap<Integer, ArrayList<String>>) bundle.get("listIng");
                hashMap2.get(1);
                ingrid = new ArrayList<>();
                ingrid = hashMap2.get(1);
                Log.d("Ingrid", "yeh "+ingrid);
                for(DataSnapshot ds : snapshot.getChildren()) {
                    Log.d("tp","gm");
                    String from = ds.child("Ingredients").getValue(String.class).toLowerCase();
                    Log.d("from",from + ingrid.size());
                    for(int j=0;j<ingrid.size();j++) {
                        Log.d("ingridd", ingrid.get(j).toString()+from);
                        if (from.toLowerCase().contains(ingrid.get(j).toLowerCase())) {
                            Log.d("ayya","ayaya");
                            list.add(ds);
                        }
                    }
                }
                ad.notifyDataSetChanged();
                Log.d("War","size"+list.size());
                for(int i = 0;i<list.size();i++){
                    Log.d("War","size"+list.get(i).child("RecipeName"));
                    al.add(list.get(i).child("RecipeName").getValue().toString());
                    String key = list.get(i).child("RecipeName").getValue().toString();
                    String value = list.get(i).toString();
                    hashMap.put(key,value);
                }
                ad.notifyDataSetChanged();


//                Intent intent = new Intent(ListRecipes.this, ListRecipes.class);
//                intent.putExtra("key", (Serializable) list);
//                startActivity(intent);
            }



        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Log.d("Yash", "chalnahi rha");

        }
    };

}
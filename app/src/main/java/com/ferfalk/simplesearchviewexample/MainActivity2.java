package com.ferfalk.simplesearchviewexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONStringer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d("War","sizeyayaya");
        myRef = database.getReference("Recipes");

        //myRef.addListenerForSingleValueEvent(valueEventListener);

        myRef.addListenerForSingleValueEvent(valueEventListener);
        Log.d("War","sizeyaya");

    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Log.d("War","size");
            if(snapshot.exists()){
                List<DataSnapshot> list = new ArrayList<>();
                List<String> ingrid = new  ArrayList<>();
                ingrid.add("milk");
                ingrid.add("chicken");
                for(DataSnapshot ds : snapshot.getChildren()) {
                    String from = ds.child("Ingredients").getValue(String.class).toLowerCase();
                    for( String s: ingrid) {
                        if (from.contains(s)) {
                            list.add(ds);
                        }
                    }
                }
                Log.d("War","size"+list.size());
                for(int i = 0;i<list.size();i++){
                    Log.d("War","size"+list.get(i));
                }

            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Log.d("Yash", "chalnahi rha");

        }
    };
}
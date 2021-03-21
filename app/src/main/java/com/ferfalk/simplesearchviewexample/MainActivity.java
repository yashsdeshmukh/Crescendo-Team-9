package com.ferfalk.simplesearchviewexample;

import android.content.Intent;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ferfalk.simplesearchview.SimpleSearchView;
import com.ferfalk.simplesearchview.utils.DimensUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SimpleSearchView.OnQueryTextListener, LocationListener {
    public static final int EXTRA_REVEAL_CENTER_PADDING = 40;
    private SimpleSearchView searchView;
    private TabLayout tabLayout;
    ArrayAdapter<String> ad;
    ArrayAdapter<String> ad2;
    Button done;
    ListView listView;
    List<String> al;
    HashMap<Integer,ArrayList<String>> hashMap;
    double currentLatitude, currentLongitude;
//    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//    private TabLayout.Tab tab1;
//    private TabLayout.Tab tab2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);
        tabLayout = findViewById(R.id.tabLayout);
        done = findViewById(R.id.Done);
        hashMap = new HashMap<>();
//        TabLayout.
//        tab1 = findViewById(R.id.tabItem1);
//        tab2 = findViewById(R.id.tabItem2);

//        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
//        ViewPager viewPager = findViewById(R.id.container);
//        viewPager.setAdapter(sectionsPagerAdapter);
//
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        listView = findViewById(R.id.branch);
        ListView listView_selected = findViewById(R.id.selected_branch);

        /*
        Turmeric
        Gramflour
        Carrots
        Chillies
        Onion
        Spinach
        Cloves
        Garlic
        Potatoes
        Wheat flour
        ALl purpose flour
        Olive Oil
        Cocoa powder
        Curd
        Ghee
        paneer
        Milk

         */


        al  = new ArrayList<>();

        AddItems((ArrayList<String>) this.al);

        ArrayList<String> al2  = new ArrayList<>();

//        tab2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listView.setVisibility(View.GONE);
//                listView_selected.setVisibility(View.VISIBLE);
//            }
//        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Yes done" + al2.size(), Toast.LENGTH_SHORT).show();
                System.out.println(currentLatitude +" opopopo "+currentLongitude);
//                Intent i = new Intent(
//                        Intent.ACTION_VIEW,
//                        Uri.parse("https://www.google.com/maps/search/grocery+near+me/@19.0795509,72.8831724,15.59z")
////                        Uri.parse("https://www.google.com/maps/search/grocery+store/@" + currentLatitude + ","+ currentLongitude +","+"15z/data=!3m1!4b1")
//                );
                Intent i = new Intent(MainActivity.this, ListRecipes.class);
                hashMap.put(1,new ArrayList<String>(al2));
                Log.d("Aja","has " + hashMap);
                i.putExtra("listIng",hashMap);
                startActivity(i);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        listView.setVisibility(View.VISIBLE);
                        listView_selected.setVisibility(View.GONE);
                        done.setVisibility(View.GONE);
                        break;
                    case 1:
                        listView.setVisibility(View.GONE);
                        listView_selected.setVisibility(View.VISIBLE);
                        done.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        listView.setVisibility(View.GONE);
                        listView_selected.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        this.ad = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                al
        );

        this.ad2 = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                al2
        );

        listView.setAdapter(this.ad);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Selected"+(String) parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                //al2.add(al.get(position));
//                al2.add((String) parent.getAdapter().getItem(position));
                al2.add((String) parent.getItemAtPosition(position));
            }
        });

        listView_selected.setAdapter(this.ad2);
        listView_selected.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "Selected"+al.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void AddItems(ArrayList<String> al){
        al.add("Chicken");
        al.add("Dahi");
        al.add("Cucumber");
        al.add("Sticks");
        al.add("Dahi");
        al.add("Turmeric");
        al.add("Milk");
        al.add("Ghee");
        al.add("Cocoa powder");
        al.add("ALl purpose flour");
        al.add("Wheat flour");
        al.add("Carrots");
        al.add("Gramflour");
        al.add("Potatoes");
        al.add("Fish");
        al.add("Pomfret");
        al.add("Capsicum");
        al.add("Butter");
        al.add("Egg");
        al.add("Bread");
        al.add("Cheese");
        al.add("Tomato");
        al.add("Onion");
        al.add("Olive oil");
        al.add("Drumstick");
        al.add("Coconut");
        al.add("Cabbage");
        al.add("Curd");
        al.add("Shrimp");
        al.add("Dal");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        setupSearchView(menu);
        return true;
    }

    private void setupSearchView(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        searchView.setTabLayout(tabLayout);

        // Adding padding to the animation because of the hidden menu item
        Point revealCenter = searchView.getRevealAnimationCenter();
        revealCenter.x -= DimensUtils.convertDpToPx(EXTRA_REVEAL_CENTER_PADDING, this);
    }

    @Override
    public void onBackPressed() {
        if (searchView.onBackPressed()) {
            AddItems((ArrayList<String>) this.al);
            listView.setVisibility(View.VISIBLE);
            return;
        }

        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (searchView.onActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        //***code to query the firebase DB

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        this.ad.getFilter().filter(newText);
        return false;
    }

    @Override
    public boolean onQueryTextCleared() {
        return false;
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
            // Empty
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}

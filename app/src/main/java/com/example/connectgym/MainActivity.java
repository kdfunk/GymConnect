package com.example.connectgym;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GymAdapter adapter;
    private ArrayList<Gym> gymArrayList;
    private Spinner spinnerLocation, spinnerClassType;
    private SeekBar priceRangeSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setupSpinners();
        setupRecyclerView();
        setupSeekBar();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        spinnerLocation = findViewById(R.id.spinnerLocation);
        spinnerClassType = findViewById(R.id.spinnerClassType);
        priceRangeSeekBar = findViewById(R.id.priceRangeSeekBar);
    }

    private void setupRecyclerView() {
        gymArrayList = new ArrayList<>();
        populateGymList();
        adapter = new GymAdapter(gymArrayList, this::navigateToDetail);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void navigateToDetail(Gym gym) {
        Intent intent = new Intent(this, GymDetailActivity.class);
        intent.putExtra("GYM_NAME", gym.getName());
        intent.putExtra("GYM_LOCATION", gym.getLocation());
        intent.putExtra("GYM_PRICE", gym.getPriceRange());
        intent.putExtra("GYM_IMAGE_RES_ID", gym.getImageResourceId());
        startActivity(intent);
    }

    private void setupSpinners() {
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"All Locations", "Balbriggan", "Swords", "Dublin City", "Blanchardstown"});
        spinnerLocation.setAdapter(locationAdapter);

        ArrayAdapter<String> classAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"All Classes", "Yoga", "Crossfit", "Commercial", "Fitness Centre"});
        spinnerClassType.setAdapter(classAdapter);
    }

    private void setupSeekBar() {
        priceRangeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                filterGyms();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Filtering by price up to: €" + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateGymList() {
        gymArrayList.add(new Gym("Energie Fitness", "Balbriggan", "€34.99 - €64.99", "Commercial", R.drawable.energiefitness));
        gymArrayList.add(new Gym("Ben Dunne Gym", "Blanchardstown", "€36", "Commercial", R.drawable.bendunnegym));
        gymArrayList.add(new Gym("Perpetua", "Dublin City", "€66", "Crossfit", R.drawable.perpetua));
        gymArrayList.add(new Gym("Platinum Gyms", "Balbriggan", "€45", "Commercial", R.drawable.platinumgyms));
        gymArrayList.add(new Gym("Gym Plus", "Swords", "€59", "Fitness Centre", R.drawable.gymplus));
        gymArrayList.add(new Gym("FLYEfit", "Swords", "€35 - €63", "Commercial", R.drawable.flyefit));
        gymArrayList.add(new Gym("yogahub", "Swords", "€22", "Yoga", R.drawable.yogahub));
        gymArrayList.add(new Gym("Bikram Yoga", "Blanchardstown", "€69", "Yoga", R.drawable.bikramyoga));
        gymArrayList.add(new Gym("Sport Ireland Campus", "Blanchardstown", "€45 - €65", "Fitness Centre", R.drawable.sportirelandcampus));
        gymArrayList.add(new Gym("bWellPilates", "Balbriggan", "€36 - €65", "Yoga", R.drawable.bwellpilates));
        gymArrayList.add(new Gym("SVfitness", "Dublin City", "€39.99 - €64.99", "Commercial", R.drawable.svfitness));
        gymArrayList.add(new Gym("Iveagh Fitness Club", "Dublin City", "€36.50", "Fitness Centre", R.drawable.iveaghfitnessclub));

    }

    private void filterGyms() {
        List<Gym> filteredList = new ArrayList<>();
        for (Gym gym : gymArrayList) {
            if (gymMatchesFilters(gym)) {
                filteredList.add(gym);
            }
        }
        adapter.filter(filteredList);
    }

    private boolean gymMatchesFilters(Gym gym) {
        String location = spinnerLocation.getSelectedItem().toString();
        String classType = spinnerClassType.getSelectedItem().toString();
        int maxPrice = getPriceRangeMax(gym.getPriceRange());
        return (location.equals("All Locations") || gym.getLocation().equals(location)) &&
                (classType.equals("All Classes") || gym.getClassType().contains(classType)) &&
                maxPrice <= priceRangeSeekBar.getProgress();
    }

    private int getPriceRangeMax(String priceRange) {
        priceRange = priceRange.replace("$", "").replace("€", "").trim();
        if (priceRange.contains("-")) {
            String[] parts = priceRange.split("-");
            return (int) Math.ceil(Double.parseDouble(parts[1].trim()));
        } else {
            return (int) Math.ceil(Double.parseDouble(priceRange));
        }
    }
}

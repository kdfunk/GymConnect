package com.example.connectgym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GymDetailActivity extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewLocation;
    private TextView textViewPriceRange;
    private ImageView imageViewGym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_detail);

        textViewName = findViewById(R.id.textViewDetailGymName);
        textViewLocation = findViewById(R.id.textViewDetailLocation);
        textViewPriceRange = findViewById(R.id.textViewDetailPriceRange);
        imageViewGym = findViewById(R.id.imageViewGym);

        Intent intent = getIntent();
        String name = intent.getStringExtra("GYM_NAME");
        String location = intent.getStringExtra("GYM_LOCATION");
        String price = intent.getStringExtra("GYM_PRICE");
        int imageResId = intent.getIntExtra("GYM_IMAGE_RES_ID", R.drawable.default_gym_image);

        textViewName.setText(name);
        textViewLocation.setText(location);
        textViewPriceRange.setText(price);
        imageViewGym.setImageResource(imageResId);

        ImageButton backBtn = findViewById(R.id.buttonBack);
        backBtn.setOnClickListener(view -> onBackPressed());
    }
}

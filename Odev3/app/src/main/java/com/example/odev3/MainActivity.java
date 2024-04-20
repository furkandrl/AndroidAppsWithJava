package com.example.odev3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imageView;
    private ImageButton previousButton;
    private ImageButton nextButton;
    private TextView cityNameTextView;
    private TextView cityPlateTextView;
    private TextView cityDescriptionTextView;

    private int[] cityImages = {R.drawable.istanbul, R.drawable.ankara, R.drawable.karabuk}; // Şehir resimlerinin drawable ID'leri
    private String[] cityNames;
    private String[] cityPlates;
    private String[] cityDescriptions;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityNames = getResources().getStringArray(R.array.city_names);
        cityPlates = getResources().getStringArray(R.array.city_plates);
        cityDescriptions = getResources().getStringArray(R.array.city_descriptions);

        imageView = findViewById(R.id.imageView);
        previousButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);
        cityNameTextView = findViewById(R.id.cityNameTextView);
        cityPlateTextView = findViewById(R.id.cityPlateTextView);
        cityDescriptionTextView = findViewById(R.id.cityDescriptionTextView);
        updateUI();
        previousButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.previousButton) {

            if (currentIndex > 0) {
                currentIndex--;
                updateUI();
            }
        }

        if(id == R.id.nextButton){
            if (currentIndex < cityImages.length - 1) {
                currentIndex++;
                updateUI();
            }

        }

    }

    private void updateUI() {
        imageView.setImageResource(cityImages[currentIndex]);
        cityNameTextView.setText(cityNames[currentIndex]);
        cityPlateTextView.setText(cityPlates[currentIndex]);
        cityDescriptionTextView.setText(cityDescriptions[currentIndex]);

        if (currentIndex == 0) {
            previousButton.setVisibility(View.INVISIBLE);
            nextButton.setVisibility(View.VISIBLE);
        } else if (currentIndex == cityImages.length - 1) {
            previousButton.setVisibility(View.VISIBLE);
            nextButton.setVisibility(View.INVISIBLE);
        } else {
            previousButton.setVisibility(View.VISIBLE);
            nextButton.setVisibility(View.VISIBLE);
        }
    }
}
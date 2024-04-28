package com.example.food_calendar;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private EditText currentWeightEditText;
    private EditText desiredWeightEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        currentWeightEditText = findViewById(R.id.current_weight_edittext);
        desiredWeightEditText = findViewById(R.id.desired_weight_edittext);


    }


    private void loadCurrentWeight() {

    }

    private void loadDesiredWeight() {

    }


}


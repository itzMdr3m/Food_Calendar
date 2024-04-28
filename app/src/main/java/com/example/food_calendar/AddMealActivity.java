package com.example.food_calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddMealActivity extends AppCompatActivity {

    public static final String EXTRA_MEAL_NAME = "extra_meal_name";
    public static final String EXTRA_MEAL_TIME = "extra_meal_time";

    private EditText mealNameEditText;
    private EditText mealTimeEditText;
    private Button addMealButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        mealNameEditText = findViewById(R.id.meal_name_edit_text);
        mealTimeEditText = findViewById(R.id.meal_time_edit_text);
        addMealButton = findViewById(R.id.add_meal_button);  // Corrected here

        addMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mealName = mealNameEditText.getText().toString().trim();
                String mealTime = mealTimeEditText.getText().toString().trim();

                if (mealName.isEmpty() || mealTime.isEmpty()) {
                    return;
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra(EXTRA_MEAL_NAME, mealName);
                resultIntent.putExtra(EXTRA_MEAL_TIME, mealTime);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
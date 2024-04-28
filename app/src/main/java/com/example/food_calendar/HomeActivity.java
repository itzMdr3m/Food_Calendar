package com.example.food_calendar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private static final int ADD_MEAL_REQUEST_CODE = 100;

    // UI element references
    private CalendarView calendarView;
    private TextView selectedDateTextView;
    private Button goToTodayButton;
    private RecyclerView breakfastRecyclerView, lunchRecyclerView, dinnerRecyclerView;
    private BottomNavigationView navigationBar;

    // Data and adapters
    private SharedPreferences sharedPreferences;
    private List<Meal> breakfastMeals, lunchMeals, dinnerMeals;
    private BreakfastAdapter breakfastAdapter;
    private LunchAdapter lunchAdapter;
    private DinnerAdapter dinnerAdapter;

    // SharedPreferences keys
    private static final String PREFS_NAME = "food_calendar_prefs";
    private static final String KEY_BREAKFAST_MEALS = "breakfast_meals";
    private static final String KEY_LUNCH_MEALS = "lunch_meals";
    private static final String KEY_DINNER_MEALS = "dinner_meals";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        calendarView = findViewById(R.id.calendar_view);
        selectedDateTextView = findViewById(R.id.selected_date_text);
        goToTodayButton = findViewById(R.id.go_to_today_button);
        breakfastRecyclerView = findViewById(R.id.breakfast_recycler_view);
        lunchRecyclerView = findViewById(R.id.lunch_recycler_view);
        dinnerRecyclerView = findViewById(R.id.dinner_recycler_view);
        navigationBar = findViewById(R.id.navigation_bar);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        breakfastMeals = new ArrayList<>();
        lunchMeals = new ArrayList<>();
        dinnerMeals = new ArrayList<>();

        loadMealsFromSharedPreferences();

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDateTextView.setText(formatDate(year, month, dayOfMonth));
            updateMealListsForSelectedDate(year, month, dayOfMonth);
        });

        breakfastAdapter = new BreakfastAdapter(breakfastMeals);
        breakfastRecyclerView.setAdapter(breakfastAdapter);
        lunchAdapter = new LunchAdapter(lunchMeals);
        lunchRecyclerView.setAdapter(lunchAdapter);
        dinnerAdapter = new DinnerAdapter(dinnerMeals);
        dinnerRecyclerView.setAdapter(dinnerAdapter);

        navigationBar.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_add_meal) {
                Intent intent = new Intent(HomeActivity.this, AddMealActivity.class);
                startActivityForResult(intent, ADD_MEAL_REQUEST_CODE);
                return true;
            }
            return false;
        });

        goToTodayButton.setOnClickListener(v -> calendarView.setDate(Calendar.getInstance().getTimeInMillis()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_MEAL_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                String mealName = data.getStringExtra(AddMealActivity.EXTRA_MEAL_NAME);
                String mealTime = data.getStringExtra(AddMealActivity.EXTRA_MEAL_TIME);
                int selectedYear = (int) calendarView.getDate();
                int selectedMonth = calendarView.getMonth();
                int selectedDay = calendarView.getDayOfMonth();

                if (isBreakfastTime(mealTime)) {
                    breakfastMeals.add(new Meal(mealName, mealTime));
                } else if (isLunchTime(mealTime)) {
                    lunchMeals.add(new Meal(mealName, mealTime));
                } else {
                    dinnerMeals.add(new Meal(mealName, mealTime));
                }

                updateMealListsForSelectedDate(selectedYear, selectedMonth, selectedDay);
                saveMealsToSharedPreferences();
            }
        }
    }

    private void updateMealListsForSelectedDate(int year, int month, int day) {

        breakfastAdapter.clear();
        lunchAdapter.clear();
        dinnerAdapter.clear();

        for (Meal meal : breakfastMeals) {
            Calendar mealCalendar = Calendar.getInstance();
            mealCalendar.setTimeInMillis(getMillisFromDate(meal.getDate()));

            if (mealCalendar.get(Calendar.YEAR) == year &&
                    mealCalendar.get(Calendar.MONTH) == month &&
                    mealCalendar.get(Calendar.DAY_OF_MONTH) == day) {
                if (isBreakfastTime(meal.getMealTime())) {
                    breakfastAdapter.add(meal);
                } else if (isLunchTime(mealTime)) {
                    lunchAdapter.add(meal);
                } else {
                    dinnerAdapter.add(meal);
                }
            }
        }

        breakfastAdapter.notifyDataSetChanged();
        lunchAdapter.notifyDataSetChanged();
        dinnerAdapter.notifyDataSetChanged();
    }

    private long getMillisFromDate(String date) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate localDate = LocalDate.parse(date);
            return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        } else {
            return 0;
        }
    }

    private boolean isBreakfastTime(String mealTime) {
        int hour = Integer.parseInt(mealTime.substring(0, 2));
        return hour >= 7 && hour < 11;
    }

    private boolean isLunchTime(String mealTime) {
        int hour = Integer.parseInt(mealTime.substring(0, 2));
        return hour >= 11 && hour < 15;
    }

    private boolean isDinnerTime(String mealTime) {
        int hour = Integer.parseInt(mealTime.substring(0, 2));
        return hour >= 17 || hour < 7;
    }
}
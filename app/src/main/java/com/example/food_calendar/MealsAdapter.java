package com.example.food_calendar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.MealViewHolder> {

    protected List<Meal> meals;

    public MealsAdapter(List<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public abstract MealViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(MealViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.mealNameTextView.setText(meal.getName());
        holder.mealTimeTextView.setText(meal.getTime());
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public static class MealViewHolder extends RecyclerView.ViewHolder {

        TextView mealNameTextView;
        TextView mealTimeTextView;

        public MealViewHolder(View itemView) {
            super(itemView);
            mealNameTextView = itemView.findViewById(R.id.meal_name_text_view);
            mealTimeTextView = itemView.findViewById(R.id.meal_time_text_view);
        }
    }
}

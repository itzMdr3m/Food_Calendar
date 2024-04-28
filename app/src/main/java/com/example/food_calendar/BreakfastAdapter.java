package com.example.food_calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BreakfastAdapter extends RecyclerView.Adapter<BreakfastAdapter.BreakfastViewHolder> {

    private List<Meal> breakfastMeals;

    public BreakfastAdapter(List<Meal> meals) {
        this.breakfastMeals = meals;
    }

    @NonNull
    @Override
    public BreakfastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_item, parent, false);
        return new BreakfastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BreakfastViewHolder holder, int position) {
        Meal meal = breakfastMeals.get(position);
        holder.mealNameTextView.setText(meal.getName());
        holder.mealTimeTextView.setText(meal.getTime());  // Assuming these TextViews exist in meal_item.xml
    }

    @Override
    public int getItemCount() {
        return breakfastMeals.size();
    }

    public static class BreakfastViewHolder extends RecyclerView.ViewHolder {

        private TextView mealNameTextView;
        private TextView mealTimeTextView;

        public BreakfastViewHolder(View itemView) {
            super(itemView);
            mealNameTextView = itemView.findViewById(R.id.meal_name_text_view);  // Assuming these IDs exist
            mealTimeTextView = itemView.findViewById(R.id.meal_time_text_view);
        }
    }
}

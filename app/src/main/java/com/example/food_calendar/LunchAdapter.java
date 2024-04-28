package com.example.food_calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LunchAdapter extends RecyclerView.Adapter<LunchAdapter.LunchViewHolder> {

    private final List<Meal> lunchMeals;

    public LunchAdapter(List<Meal> lunchMeals) {
        this.lunchMeals = lunchMeals;
    }

    @NonNull
    @Override
    public LunchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lunch_item, parent, false);
        return new LunchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LunchViewHolder holder, int position) {
        Meal meal = lunchMeals.get(position);
        holder.mealNameTextView.setText(meal.getName());
        holder.mealTimeTextView.setText(meal.getTime());
    }

    @Override
    public int getItemCount() {
        return lunchMeals.size();
    }

    public static class LunchViewHolder extends RecyclerView.ViewHolder {

        private TextView mealNameTextView;
        private TextView mealTimeTextView;

        public LunchViewHolder(View itemView) {
            super(itemView);
            mealNameTextView = itemView.findViewById(R.id.meal_name_text_view);
            mealTimeTextView = itemView.findViewById(R.id.meal_time_text_view);
        }
    }
}

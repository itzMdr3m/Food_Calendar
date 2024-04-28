package com.example.food_calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DinnerAdapter extends RecyclerView.Adapter<DinnerAdapter.DinnerViewHolder> {

    private List<Meal> dinnerMeals;

    public DinnerAdapter(List<Meal> dinnerMeals) {
        this.dinnerMeals = dinnerMeals;
    }

    @NonNull
    @Override
    public DinnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dinner_item, parent, false);
        return new DinnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DinnerViewHolder holder, int position) {
        Meal meal = dinnerMeals.get(position);
        holder.mealNameTextView.setText(meal.getName());
        holder.mealTimeTextView.setText(meal.getTime());  // Assuming these TextViews exist in dinner_item.xml
    }

    @Override
    public int getItemCount() {
        return dinnerMeals.size();
    }

    public static class DinnerViewHolder extends RecyclerView.ViewHolder {

        private TextView mealNameTextView;
        private TextView mealTimeTextView;

        public DinnerViewHolder(View itemView) {
            super(itemView);
            mealNameTextView = itemView.findViewById(R.id.meal_name_text_view);  // Assuming these IDs exist
            mealTimeTextView = itemView.findViewById(R.id.meal_time_text_view);
        }
    }
}

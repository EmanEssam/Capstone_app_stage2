package com.peacefullwarrior.eman.a30dayschallenge.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.peacefullwarrior.eman.a30dayschallenge.R;
import com.peacefullwarrior.eman.a30dayschallenge.ui.model.Habit;
import com.peacefullwarrior.eman.a30dayschallenge.ui.utils.TinyDB;

import java.util.ArrayList;
import java.util.List;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.HabitViewHolder> {
    private int currentMonthDays;
    private Context context;
    private List<Habit> habits = new ArrayList<>();
    private Boolean selected = true;
    private int selectedPosition = -1;


    public HabitAdapter() {
    }

    public HabitAdapter(int currentMonthDays, Context context, List<Habit> habits) {
        this.currentMonthDays = currentMonthDays;
        this.context = context;
        this.habits = habits;
    }

    @NonNull
    @Override
    public HabitAdapter.HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.habit_row, parent, false);
        return new HabitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HabitAdapter.HabitViewHolder holder, final int position) {
        holder.currentDay.setText(position + 1 + "");
        holder.currentDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.currentDay.isSelected()) {
                    holder.currentDay.setSelected(false);

                } else {
                    holder.currentDay.setSelected(true);
                }
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("habits");
                Habit habit = new Habit(8, position, true);
//                myRef.child("task").setValue(task);
                myRef.push().setValue(habit);

            }
        });
    }

    @Override
    public int getItemCount() {
        return currentMonthDays;
    }

    public class HabitViewHolder extends RecyclerView.ViewHolder {
        TextView currentDay;

        public HabitViewHolder(View itemView) {
            super(itemView);
            currentDay = itemView.findViewById(R.id.current_day_tv);
        }
    }
}

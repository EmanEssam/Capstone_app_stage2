package com.peacefullwarrior.eman.a30dayschallenge.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.peacefullwarrior.eman.a30dayschallenge.R;
import com.peacefullwarrior.eman.a30dayschallenge.ui.adapter.HabitAdapter;
import com.peacefullwarrior.eman.a30dayschallenge.ui.adapter.MyTasksAdapter;
import com.peacefullwarrior.eman.a30dayschallenge.ui.model.Habit;
import com.peacefullwarrior.eman.a30dayschallenge.ui.model.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class HabitFragment extends Fragment {
    private RecyclerView mTasksList;
    HabitAdapter myTasksAdapter;
    private List<Habit> habits;
    DatabaseReference habitList;


    public HabitFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        habitList = FirebaseDatabase.getInstance().getReference("habits");
        habitList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (habits != null) {
                    habits.clear();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Habit task = dataSnapshot1.getValue(Habit.class);
                        habits.add(task);

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(getString(R.string.habit));
        View view = inflater.inflate(R.layout.fragment_habit, container, false);

        mTasksList = view.findViewById(R.id.my_habits_rv);
        mTasksList.setLayoutManager(new GridLayoutManager(getContext(), 6));
        myTasksAdapter = new HabitAdapter(getCurrentMonthDays(), getContext(), habits);
        mTasksList.setAdapter(myTasksAdapter);
        mTasksList.setHasFixedSize(true);
        return view;

    }

    private int getCurrentMonthDays() {

        final Calendar cal = Calendar.getInstance();
        String month_name = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
        int current_day = cal.get(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        SimpleDateFormat df = new SimpleDateFormat("MMM d, yyyy");
        final List<String> monthCalendar = new ArrayList<>();
        return maxDay;
    }

}

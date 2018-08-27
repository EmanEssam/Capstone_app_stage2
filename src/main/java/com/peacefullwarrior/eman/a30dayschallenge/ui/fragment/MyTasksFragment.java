package com.peacefullwarrior.eman.a30dayschallenge.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import com.peacefullwarrior.eman.a30dayschallenge.ui.adapter.MyTasksAdapter;
import com.peacefullwarrior.eman.a30dayschallenge.ui.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyTasksFragment extends Fragment {
    private RecyclerView mTasksList;
    private LinearLayoutManager layoutManager;
    MyTasksAdapter myTasksAdapter;
    DatabaseReference taskList;
    List<Task> tasks = new ArrayList<>();


    public MyTasksFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        taskList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tasks.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Task task = dataSnapshot1.getValue(Task.class);
                    tasks.add(task);
                }
                myTasksAdapter = new MyTasksAdapter(tasks,getContext());
                mTasksList.setLayoutManager(layoutManager);
                mTasksList.setAdapter(myTasksAdapter);
                mTasksList.setHasFixedSize(true);
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
        getActivity().setTitle(getString(R.string.my_tasks));
        View view = inflater.inflate(R.layout.fragment_my_tasks, container, false);
        taskList = FirebaseDatabase.getInstance().getReference("tasks");
        mTasksList = view.findViewById(R.id.my_tasks_rv);
        layoutManager = new LinearLayoutManager(getContext());
        return view;
    }

}

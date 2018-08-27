package com.peacefullwarrior.eman.a30dayschallenge.ui.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.peacefullwarrior.eman.a30dayschallenge.R;
import com.peacefullwarrior.eman.a30dayschallenge.ui.model.Task;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditDailyRoutineActivity extends AppCompatActivity {
    private Button mUpdatTaskBtn;
    private TextView mTitleTv;
    private TextView mDescriptionTv;
    private static TextView mDateTv;
    private static String taskDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_daily_routine);
        initViews();
        mUpdatTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write a message to the database
                if (TextUtils.isEmpty(mTitleTv.getText())) {
                    mTitleTv.setError(getString(R.string.task_title_is_empty));
                } else {
                    final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
                    Query query = dbRef
                            .child("daily_routine").orderByChild("id").equalTo(getTaskId());
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                Log.d("test", dataSnapshot1.getKey() + "  " + dataSnapshot1.getValue(Task.class)
                                        .toString());
                                updateTask(dbRef, dataSnapshot1.getKey(), 6, mTitleTv.getText().toString(), mDescriptionTv.getText().toString(), taskDate, 3);
                            }
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                            Log.e("update", databaseError.getMessage());
                        }
                    });

                }
                finish();
            }
        });
        mDateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });


    }

    private void updateTask(DatabaseReference db, String key, int id, String title, String decription, String date, int type) {
        Task task = new Task(id, title, decription, date, type);
        Map<String, Object> postValues = task.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(key, postValues);
        childUpdates.put("/daily_routine/" + id + "/" + key, postValues);


        db.updateChildren(childUpdates);
    }

    private void showTimePickerDialog() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(EditDailyRoutineActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                String status;
                if (selectedHour > 11) {
                    // If the hour is greater than or equal to 12
                    // Then the current AM PM status is PM
                    status = "PM";
                } else {
                    status = "AM";
                }
                taskDate = selectedHour + ":" + selectedMinute + " " + status;
                mDateTv.setText(taskDate);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    private void initViews() {
        mUpdatTaskBtn = findViewById(R.id.edit_task_btn);
        mTitleTv = findViewById(R.id.task_title);
        mDescriptionTv = findViewById(R.id.task_desc);
        mDateTv = findViewById(R.id.task_date);


    }

}

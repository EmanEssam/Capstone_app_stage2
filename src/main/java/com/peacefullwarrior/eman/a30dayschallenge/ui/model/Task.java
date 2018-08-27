package com.peacefullwarrior.eman.a30dayschallenge.ui.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Task {

    int id;
    String task_title;
    String task_desc;
    String date;
    int type; // 1 for task , 2 for event and 3 for To Buy List

    public Task() {
    }


    public Task(int id, String task_title, String task_desc, String date, int type) {

        this.id = id;
        this.task_title = task_title;
        this.task_desc = task_desc;
        this.date = date;
        this.type = type;

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("task_title", task_title);
        result.put("task_desc", task_desc);
        result.put("date", date);
        result.put("type", type);


        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask_title() {
        return task_title;
    }

    public void setTask_title(String task_title) {
        this.task_title = task_title;
    }

    public String getTask_desc() {
        return task_desc;
    }

    public void setTask_desc(String task_desc) {
        this.task_desc = task_desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

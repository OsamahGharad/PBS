package com.example.pbs.Tasks;

import android.content.Context;

public class TaskRepository {

    private  static  TaskRepository ourInstance;
    public static TaskRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new TaskRepository(context.getApplicationContext());
        }

        return ourInstance;
    }

    public TaskRepository(Context context) {

    }
}

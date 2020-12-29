package com.example.pbs.Tasks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TaskDetails_JsonArray {

    @SerializedName("task_details")
    @Expose
    private List<Task>task_details=null;


    public void setTask_details(List<Task> task_details) {
        this.task_details = task_details;
    }

    public List<Task> getTask_details() {
        return task_details;
    }

}

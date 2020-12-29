package com.example.pbs.Request.Responses;

import androidx.annotation.Nullable;

import com.example.pbs.LoginActivity.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("user")
    @Expose()
    private User user;
    @SerializedName("Error")
    @Expose()
    private String Error;
    @Nullable
    public User getUser() {
        return user;
    }



    public String getError() {
        return Error;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "user=" + user +
                ", Error='" + Error + '\'' +
                '}';
    }
}

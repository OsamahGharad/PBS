package com.example.pbs.Request.Responses;

import com.example.pbs.CurrentLocation.models.CurrentLocationCoordinate;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationResponse {
    @SerializedName("currentLocationCoordinate")
    @Expose()
    private CurrentLocationCoordinate currentLocationCoordinate;
    @SerializedName("Error")
    @Expose()
    private  String Error;

    public CurrentLocationCoordinate getCurrentLocationCoordinate() {
        return currentLocationCoordinate;
    }

    public String getError() {
        return Error;
    }

    @Override
    public String toString() {
        return "LocationResponse{" +
                "currentLocationCoordinate=" + currentLocationCoordinate +
                ", Error='" + Error + '\'' +
                '}';
    }
}

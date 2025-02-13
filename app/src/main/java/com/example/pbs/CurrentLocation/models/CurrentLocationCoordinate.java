package com.example.pbs.CurrentLocation.models;

public class CurrentLocationCoordinate {
    double lat;
    double lng;

    public CurrentLocationCoordinate() {

    }

    public CurrentLocationCoordinate(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "CurrentLocationCoordinate{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}

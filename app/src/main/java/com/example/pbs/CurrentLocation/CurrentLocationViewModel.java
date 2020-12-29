package com.example.pbs.CurrentLocation;

import android.app.Application;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pbs.CurrentLocation.models.CurrentLocationCoordinate;

public class CurrentLocationViewModel extends AndroidViewModel {


    private TrackingRepository repository;
    private LiveData<Location> locationLiveData;
    private  LiveData<String> locationFailed;
   private LiveData<CurrentLocationCoordinate> locationLiveData1;
    private LiveData<Boolean> trackingState;

    public CurrentLocationViewModel(@NonNull Application application) {
        super(application);
        repository = TrackingRepository.getInstance(application);
        locationLiveData = repository.getLocationLiveData();
//        locationLiveData1 = repository.getLocationLiveData1();
        trackingState = repository.getTrackingState();
        locationFailed=repository.getLocationfailedLivedata();
    }

    public void requestLocationUpdates() {
        repository.requestLocationUpdates();
    }

    public void removeLocationUpdates() {
        repository.removeLocationUpdates();
    }

    public LiveData<Location> getLocationLiveData() {
        return locationLiveData;
    }
    public LiveData<String> getLocationfailedLivedata() {
        return locationFailed;
    }
    public LiveData<CurrentLocationCoordinate> getLocationLiveData1() {
        return locationLiveData1;
    }

    public LiveData<Boolean> getTrackingState() {
        return trackingState;
    }
}

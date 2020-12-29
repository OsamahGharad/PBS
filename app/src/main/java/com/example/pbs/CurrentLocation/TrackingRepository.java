package com.example.pbs.CurrentLocation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pbs.Request.BackOfficeApi;
import com.example.pbs.Request.ServiceGenerator;
import com.example.pbs.CurrentLocation.models.CurrentLocationCoordinate;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class TrackingRepository {
    private static TrackingRepository ourInstance;

    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;
    private BackOfficeApi backOfficeApi;
    private ServiceGenerator serviceGenerator;
    private CurrentLocationCoordinate currentLocationCoordinate;
    private  Location mlocation;
    private MutableLiveData<String>  locationFailed=new MutableLiveData<>();
    private MutableLiveData<Location> locationLiveData = new MutableLiveData<>();
    private MutableLiveData<CurrentLocationCoordinate> locationLiveData1= new MutableLiveData<>();
    private MutableLiveData<Boolean> trackingState = new MutableLiveData<>();
    private String Tag="TrackingRepository";
    public static TrackingRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new TrackingRepository(context.getApplicationContext());
        }

        return ourInstance;
    }

    @SuppressLint("MissingPermission")
    private TrackingRepository(Context applicationContext) {
        // Init
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(applicationContext);

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(12 * 1000)
                .setFastestInterval(10 * 100);
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Log.e(Tag,locationResult.getLastLocation()+" "+" update");

                locationLiveData.setValue(locationResult.getLastLocation());


            }

            @Override
            public void onLocationAvailability(LocationAvailability locationAvailability) {
                super.onLocationAvailability(locationAvailability);
                Log.e(Tag,"onLocationAvailability");

            }
        };
        trackingState.setValue(false);
        mFusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        Log.e(Tag,location+" "+"last");

                        locationLiveData.setValue(location);
                        if(location==null)
                        {
                            requestLocation();

                        }

                    }
                }).addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {

            }
        });

        Log.e(Tag,locationLiveData+"");

    }
    public LiveData<String> getLocationfailedLivedata() {
        return locationFailed;
    }
    public LiveData<Location> getLocationLiveData() {
        return locationLiveData;
    }
//    public LiveData<CurrentLocationCoordinate> getLocationLiveData1() {
//        return locationLiveData1;
//    }

    public LiveData<Boolean> getTrackingState() {
        return trackingState;
    }
   public  void  requestLocation()
   {
       mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
   }
    @SuppressLint("MissingPermission")
    public void requestLocationUpdates() {
        removeLocationUpdates();
        trackingState.setValue(true);
        mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
        Log.e(Tag," update");

    }

    public void removeLocationUpdates() {
        trackingState.setValue(false);
        mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
    }

}

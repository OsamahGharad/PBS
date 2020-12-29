package com.example.pbs.CurrentLocation;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.Observer;

import com.example.pbs.Activity.MainActivity;
import com.example.pbs.LoginActivity.TokenManager;
import com.example.pbs.Request.BackOfficeApi;
import com.example.pbs.Request.RetrofitBuilder;
import com.example.pbs.Request.ServiceGenerator;
import com.example.pbs.CurrentLocation.models.CurrentLocationCoordinate;
import com.example.pbs.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentLocationService extends LifecycleService {
    private static final String TAG="CurrentLocationService";
    private static final int NOTIFICATION_ID = 6438;
    private static final String CHANNEL_ID = "foreground";
    private CurrentLocationViewModel myViewModel;
    private  BackOfficeApi backOfficeApi;
    private ServiceGenerator serviceGenerator;
    private CurrentLocationCoordinate currentLocationCoordinate;
    private TokenManager tokenManager;
    @Override
    public void onCreate() {
        super.onCreate();
        currentLocationCoordinate=new CurrentLocationCoordinate();
       startForeground(NOTIFICATION_ID, getMyNotification(""));
       tokenManager=TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        stopForeground(true);
//        stopSelf();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        flags = START_STICKY;
        createNotificationChannel();
        myViewModel = new CurrentLocationViewModel(getApplication());
        startObservingLocationLivedata();
        return START_STICKY;
    }

    private void startObservingLocationLivedata() {
        myViewModel.getLocationLiveData().observe(this, new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
//                Location location1=location;



                updateNotification(location.toString());
                sendCurrentLocation(location);
            }
        });
    }
    public  void sendCurrentLocation(Location location)
    {  currentLocationCoordinate.setLat(location.getAltitude());
        currentLocationCoordinate.setLng(location.getLongitude());
       Log.e(TAG,location.toString());
        Log.e(TAG,currentLocationCoordinate.toString());
        backOfficeApi= RetrofitBuilder.createServiceWithAuth(BackOfficeApi.class,tokenManager);
        Call<String> call= backOfficeApi.sendCurrentLocation(currentLocationCoordinate);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Log.e(TAG, response.isSuccessful()+"");
                if (response.isSuccessful()) {
                    Log.e(TAG, response.body()+"successful connection");
      // currentLocationCoordinate.setLng(response.body().getLng());
     //  currentLocationCoordinate.setLat(response.body().getLat());
     //  locationLiveData1.setValue(currentLocationCoordinate);
                }
                else
                {
                   Log.e(TAG,response.body()+"notsuccess");
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "failed connection");
//                Log.e(TAG, t.getMessage());
//                Log.e(TAG, t.getStackTrace().toString());
//                Log.e(TAG, t.getLocalizedMessage());
              //  locationFailed.setValue("failed connection");

            }
        });

    }
    private Notification getMyNotification(String message) {

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Current Location")
                .setContentText(message)
                .setOnlyAlertOnce(true)
                .setOngoing(true)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSmallIcon(R.drawable.ic_location_on_black_24dp)
                .setContentIntent(contentIntent)
                .build();
    }

    private void updateNotification(String message) {
        Notification notification = getMyNotification(message);
        NotificationManagerCompat.from(getApplicationContext()).notify(NOTIFICATION_ID, notification);
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManagerCompat.from(getApplicationContext()).createNotificationChannel(channel);
        }
    }
}

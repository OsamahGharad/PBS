package com.example.pbs.PushNotification;

import android.app.PendingIntent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.pbs.Activity.MainActivity;
import com.example.pbs.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFireBaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFireBaseMessagingService.class.getSimpleName();

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.i(getString(R.string.DEBUG_TAG), "New Token: "+s);
    }



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.i(getString(R.string.DEBUG_TAG),"Message received");
        ((myApplication)getApplication()).triggerNotification(MainActivity.class,
                getString(R.string.NEWS_CHANNEL_ID),
                remoteMessage.getNotification().getTitle(),
                remoteMessage.getNotification().getBody(),
                "This notification is from FCM console ",
                NotificationCompat.PRIORITY_HIGH,
                true,
                getResources().getInteger(R.integer.notificationId),
                PendingIntent.FLAG_UPDATE_CURRENT);
    }
}

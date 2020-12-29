package com.example.pbs.PushNotification;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;

import com.example.pbs.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class myApplication extends Application {

    private static final String TAG = myApplication.class.getSimpleName();
    MyAppsNotificationManager  myAppsNotificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        myAppsNotificationManager = MyAppsNotificationManager.getInstance(this);
        myAppsNotificationManager.registerNotificationChannelChannel(
                getString(R.string.NEWS_CHANNEL_ID),
                getString(R.string.CHANNEL_NEWS),
                getString(R.string.CHANNEL_DESCRIPTION));

        FirebaseMessaging.getInstance().setAutoInitEnabled(true);

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if(!task.isSuccessful()) {
                    Log.i(getString(R.string.DEBUG_TAG), "Task Failed");
                    return;
                }
                Log.i(getString(R.string.DEBUG_TAG), "The result: "+task.getResult().getToken());
            }
        });

    }

    public void triggerNotification(Class targetNotificationActivity, String channelId, String title, String text, String bigText, int priority, boolean autoCancel, int notificationId, int pendingIntentFlag){
        Log.i(getString(R.string.DEBUG_TAG), "triggerNotification");
        myAppsNotificationManager.triggerNotification(targetNotificationActivity,channelId,title,text, bigText, priority, autoCancel,notificationId, pendingIntentFlag);
    }





}

package com.example.zeeshan.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Zeeshan on 12/5/2017.
 */

public class BoundService extends Service {
    private static final int ID_NOTIFICATION=0;
    long startTime=0;
    long endTime=0;
    private MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startTime= System.currentTimeMillis();

        //getting systems default ringtone
//        player = MediaPlayer.create(this,
//                Settings.System.DEFAULT_RINGTONE_URI);
//        player.setLooping(true);
//
//        //staring the player
//        player.start();
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        endTime=System.currentTimeMillis();
        //getting the total time span for call in milli second
        long timeInMiliSecond=endTime-startTime;
        //time in seconds
        timeInMiliSecond=timeInMiliSecond/1000;

        createNotification(String.valueOf(timeInMiliSecond)+" seconds ");

        //stopping the player when service is destroyed
        //player.stop();
    }
    private void createNotification(String message)

    {
        Intent intent = new Intent(this, BoundService.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);

        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Call Durations")
                .setContentText(message)
                .setAutoCancel(true)
                .setOngoing(true)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(ID_NOTIFICATION , notificationBuilder.build());
    }
}

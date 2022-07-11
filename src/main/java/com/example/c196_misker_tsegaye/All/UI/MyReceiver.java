package com.example.c196_misker_tsegaye.All.UI;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.c196_misker_tsegaye.R;

public class MyReceiver extends BroadcastReceiver {
    String channel_id = "channel";
    static int notificationID;


    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra("key"), Toast.LENGTH_LONG).show();
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        createNotificationChannel(context, channel_id);
        Notification n = new NotificationCompat.Builder(context, channel_id)
            .setSmallIcon(R.drawable.ic_baseline_add_alert_24)
                .setContentText(intent.getStringExtra("key"))
                .setContentTitle("Notification").build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID++, n);

    }

    private void createNotificationChannel(Context context, String CHANNEL_ID){
        CharSequence name = context.getResources().getString(R.string.channel_name);
        String description = context.getString(R.string.channel_descripton);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}
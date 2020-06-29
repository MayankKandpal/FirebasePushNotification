package com.example.firebasepushnotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.text.ParseException;

public class MyService extends FirebaseMessagingService {
    public MyService() {
    }
    public static int NOTIFICATION_ID=1;
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        generateNotification(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());
    }

    private void generateNotification(String body, String title) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri SoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationbuilder = new NotificationCompat.Builder(this).
                setSmallIcon(R.mipmap.ic_launcher).setContentTitle(title).setContentText(body).
                setAutoCancel(true).setSound(SoundUri).setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if(NOTIFICATION_ID>10782828){
            NOTIFICATION_ID=0;
        }
        notificationManager.notify(NOTIFICATION_ID++,notificationbuilder.build());
  }
}

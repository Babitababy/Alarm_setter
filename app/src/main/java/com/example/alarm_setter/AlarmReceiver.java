package com.example.alarm_setter;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class AlarmReceiver extends BroadcastReceiver
{
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onReceive(Context context, Intent intent)
    {
        //we will use vibrator first
        Vibrator vibrator = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);

        //now setup the notification
        Notification noti=new Notification.Builder(context).setContentTitle("ALARM")
                .setContentText("You had set up the alarm")
                .setSmallIcon(R.mipmap.ic_launcher).build();
        NotificationManager manager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        noti.flags=Notification.FLAG_AUTO_CANCEL;
        manager.notify(0,noti);

        //finally we can play the ringtone

        Uri notification= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone r= RingtoneManager.getRingtone(context,notification);
        r.play();


    }
}
package com.example.alarm_setter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;
public class AlarmReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context, "Wake up! Wake up!", Toast.LENGTH_LONG).show();
        Uri alarm_uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarm_uri == null)
        {
            alarm_uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        //Ringtone rings when alarm time reaches
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarm_uri);
        ringtone.play();

    }
}
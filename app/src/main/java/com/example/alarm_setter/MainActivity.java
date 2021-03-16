package com.example.alarm_setter;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.util.Calendar;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    TimePicker time_Picker;
    AlarmManager manager;
    PendingIntent pending_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time_Picker = (TimePicker) findViewById(R.id.time_picker);
        manager = (AlarmManager) getSystemService(ALARM_SERVICE);

    }
    public void OnToggleClicked(View view)
    {
        long alarm_time;

        if (((ToggleButton) view).isChecked())
        {

            Toast.makeText(MainActivity.this, "ALARM ON", Toast.LENGTH_SHORT).show();
            Calendar calendar = Calendar.getInstance();
            //get current hour
            calendar.set(Calendar.HOUR_OF_DAY, time_Picker.getCurrentHour());
            //get ccurrent minute
            calendar.set(Calendar.MINUTE, time_Picker.getCurrentMinute());
            Intent intent = new Intent(this, AlarmReceiver.class);
            pending_intent = PendingIntent.getBroadcast(this, 0, intent, 0);

            alarm_time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
            if(System.currentTimeMillis()>alarm_time)
            {
                if (calendar.AM_PM == 0)
                    alarm_time  = alarm_time + (1000*60*60*12);
                else
                    alarm_time = alarm_time + (1000*60*60*24);
            }
            //alarm is repeated until it is turned off by the uer
            manager.setRepeating(AlarmManager.RTC_WAKEUP, alarm_time, 10000, pending_intent);

        }

        else
        {
            manager.cancel(pending_intent);
            //alarm off
            Toast.makeText(MainActivity.this, "ALARM OFF", Toast.LENGTH_LONG).show();
        }

    }
}


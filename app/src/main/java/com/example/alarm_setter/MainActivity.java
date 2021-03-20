package com.example.alarm_setter;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    TimePicker time_Picker;
    TextView textView;
    int mHour,mMin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time_Picker = (TimePicker) findViewById(R.id.time_picker);
        textView=(TextView)findViewById(R.id.time_textview);

        time_Picker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mHour=hourOfDay;
                mMin=minute;
                textView.setText(textView.getText().toString()+" "+mHour+":"+mMin);


            }
        });


    }

    //Now we will implement method to implement alarm time
    public void setTimer(View v)
    {
        //Now we are going to use AlarmManager Class
      AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Date date = new Date();
        Calendar cal_Alarm = Calendar.getInstance();//this cal_Alarm
        // object is for alarm calendar time

        Calendar cal_now = Calendar.getInstance(); //this cal_now
        // object is for current time

        cal_now.setTime(date);
        cal_Alarm.setTime(date);

        //Now set Hour,Minute and second
        cal_Alarm.set(Calendar.HOUR_OF_DAY,mHour);
        cal_Alarm.set(Calendar.MINUTE,mMin);
        cal_Alarm.set(Calendar.SECOND,0);

        //check if cal_alarm is before cal_now
        if(cal_Alarm.before(cal_now))
        {
            cal_Alarm.add(Calendar.DATE,1);
        }
        //now we will simply call BroadcastReceiver
      Intent intent = new Intent(MainActivity.this,AlarmReceiver.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(MainActivity.this,24444,intent,0);
        alarmManager.set(AlarmManager.RTC_WAKEUP,cal_Alarm.getTimeInMillis(),pendingIntent);





    }


}


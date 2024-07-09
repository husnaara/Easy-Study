//package com.example.easystudy;
//
//import android.os.Bundle;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//public class SetAlarmActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_set_alarm);
//
//    }
//}

package com.example.easystudy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SetAlarmActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private Button btnSaveAlarm, btnCancelAlarm;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        dbHelper = new DatabaseHelper(this);

        timePicker = findViewById(R.id.timePicker);
        btnSaveAlarm = findViewById(R.id.btnSaveAlarm);
        btnCancelAlarm = findViewById(R.id.btnCancelAlarm);

        btnSaveAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                saveAlarm(hour, minute);
            }
        });

        btnCancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle alarm cancellation logic here if needed
                Toast.makeText(SetAlarmActivity.this, "Alarm Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveAlarm(int hour, int minute) {
        String time = String.format("%02d:%02d", hour, minute);
        boolean isInserted = dbHelper.insertAlarm(time);

        if (isInserted) {
            Toast.makeText(this, "Alarm Saved: " + time, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to Save Alarm", Toast.LENGTH_SHORT).show();
        }
    }
}

package com.dongkyoo.gongzza.home.add;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.dongkyoo.gongzza.R;

import java.util.Calendar;

public class AddClassActivity extends AppCompatActivity {
    TimePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        //AddClassActivity 다이얼로그의 각 위젯들을 생성
        EditText editTextClassName = findViewById(R.id.editTextClassName);
        EditText editTextDayOfTheWeek = findViewById(R.id.editTextDayOfTheWeek);
        EditText editTextStartTime = findViewById(R.id.editTextStartTime);
        EditText editTextEndTime = findViewById(R.id.editTextEndTime);

        editTextDayOfTheWeek.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                DayOfTheWeekDialog dayOfTheWeekDialog = new DayOfTheWeekDialog(AddClassActivity.this);

                dayOfTheWeekDialog.callFunction(editTextDayOfTheWeek);
            }
        });

        editTextStartTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);

                picker = new TimePickerDialog(AddClassActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int hourOfDay, int minute) {
                        editTextStartTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minutes, true);
                picker.show();
            }
        });
        editTextEndTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);

                picker = new TimePickerDialog(AddClassActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int hourOfDay, int minute) {
                        editTextEndTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minutes, true);
                picker.show();
            }
        });
    }
}

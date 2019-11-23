package com.dongkyoo.gongzza.home.add;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.dongkyoo.gongzza.R;
import com.dongkyoo.gongzza.home.HomeFragment;

import java.util.Calendar;

public class ClassAdd extends AppCompatActivity {
    private Context context;

    public ClassAdd(Context context) {
        this.context = context;
    }

    //호출할 다이얼로그 함수를 정의
    public void callFunction (/*final EditText editText*/) {

        //ClassAdd 다이얼로그를 정의하기 위해 Dialog클래스를 생성
        final Dialog dig = new Dialog(context);

        //액티비티의 타이틀바를 숨김
        dig.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //ClassAdd 다이얼로그의 레이아웃 설정
        dig.setContentView(R.layout.add_class);

        //ClassAdd 다이얼로그를 노출
        dig.show();

        //ClassAdd 다이얼로그의 각 위젯들을 생성
        final TimePickerDialog[] picker = new TimePickerDialog[1];
        final EditText editTextClassName = dig.findViewById(R.id.editTextClassName);
        final EditText editTextDayOfTheWeek = dig.findViewById(R.id.editTextDayOfTheWeek);
        final EditText editTextStartTime = dig.findViewById(R.id.editTextStartTime);
        final EditText editTextEndTime = dig.findViewById(R.id.editTextEndTime);

        editTextStartTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);

                picker[0] = new TimePickerDialog(ClassAdd.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int hourOfDay, int minute) {
                        editTextStartTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minutes, true);
                picker[0].show();
                dig.dismiss();
            }
        });
    }
}

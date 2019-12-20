package com.dongkyoo.gongzza.all.BreakTime;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.dongkyoo.gongzza.R;
import com.dongkyoo.gongzza.customViews.timetableView.TimetableView;
import com.dongkyoo.gongzza.dtos.CourseDto;
import com.dongkyoo.gongzza.network.CourseApi;
import com.dongkyoo.gongzza.network.Networks;
import com.dongkyoo.gongzza.network.UserApi;
import com.dongkyoo.gongzza.vos.Config;
import com.dongkyoo.gongzza.vos.CourseInfo;
import com.dongkyoo.gongzza.vos.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BreakTimeActivity extends AppCompatActivity {

    private User me;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break_time);

        String[] colorArray = getResources().getStringArray(R.array.color_list);

        EditText editText = new EditText(BreakTimeActivity.this);
        TimetableView timetableView = findViewById(R.id.timetableView);
        Button addUserTimetableButton = findViewById(R.id.add_user_timetable_button);

        me = getIntent().getParcelableExtra(Config.USER);

        CourseApi courseApi = Networks.retrofit.create(CourseApi.class);
        UserApi userApi = Networks.retrofit.create(UserApi.class);

        courseApi.selectCourseDtoListByUserId(me.getId()).enqueue(new Callback<List<CourseDto>>() {
            @Override
            public void onResponse(Call<List<CourseDto>> call, Response<List<CourseDto>> response) {
                if (response.isSuccessful()) {
                    timetableView.addUserTimetableList(new TimetableView.UserTimetable(
                            me, response.body(), colorArray[index++]
                    ));
                    index %= colorArray.length;
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "시간표 로딩 실패", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<CourseDto>> call, Throwable t) {
                Snackbar.make(findViewById(android.R.id.content), "네트워크 통신 오류", Snackbar.LENGTH_LONG).show();
            }
        });

        addUserTimetableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getParent() != null)
                    ((ViewGroup) editText.getParent()).removeView(editText);

                new AlertDialog.Builder(BreakTimeActivity.this)
                        .setTitle("시간표 추가")
                        .setView(editText)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String id = editText.getText().toString();
                                if (TextUtils.isEmpty(id)) {
                                    return;
                                }

                                Call<User> userCall = userApi.getUserById(id);
                                userCall.enqueue(new Callback<User>() {
                                    @Override
                                    public void onResponse(Call<User> call, Response<User> response) {
                                        if (response.isSuccessful() && response.body() != null) {
                                            User user = response.body();
                                            Call<List<CourseDto>> courseCall = courseApi.selectCourseDtoListByUserId(id);
                                            courseCall.enqueue(new Callback<List<CourseDto>>() {
                                                @Override
                                                public void onResponse(Call<List<CourseDto>> call, Response<List<CourseDto>> response) {
                                                    if (response.isSuccessful()) {
                                                        timetableView.addUserTimetableList(new TimetableView.UserTimetable(
                                                                user, response.body(), colorArray[index++]
                                                        ));
                                                        index %= colorArray.length;
                                                        editText.setText("");
                                                    } else {
                                                        Snackbar.make(findViewById(android.R.id.content), "시간표 로딩 실패", Snackbar.LENGTH_LONG).show();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<List<CourseDto>> call, Throwable t) {
                                                    Snackbar.make(findViewById(android.R.id.content), "네트워크 통신 오류", Snackbar.LENGTH_LONG).show();
                                                }
                                            });
                                        } else {
                                            Snackbar.make(findViewById(android.R.id.content), "존재하지 않는 아이디입니다", Snackbar.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<User> call, Throwable t) {
                                        Snackbar.make(findViewById(android.R.id.content), "네트워크 통신 오류", Snackbar.LENGTH_LONG).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
            }
        });
    }
}

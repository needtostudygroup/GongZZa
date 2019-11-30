package com.dongkyoo.gongzza.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dongkyoo.gongzza.R;
import com.dongkyoo.gongzza.network.Networks;
import com.dongkyoo.gongzza.network.UserApi;
import com.dongkyoo.gongzza.vos.Config;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindAccountActivity extends AppCompatActivity {

    private UserApi userApi;
    private  String birthString;
    private Date birthDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_find_account);

        Networks.createRetrofit(this);
        userApi = Networks.retrofit.create(UserApi.class);

        EditText nameEditText = findViewById(R.id.inputName);
        DatePicker birth = (DatePicker)findViewById(R.id.inputBirth);

        findViewById(R.id.findIDButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                birth.init(birth.getYear(), birth.getMonth(), birth.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        birthString = String.format("%d-%d-%d", birth.getYear(), birth.getMonth() + 1, birth.getDayOfMonth());
                        birthDate = Date.valueOf(birthString);
                    }
                });

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(FindAccountActivity.this, "이름을 입력하세요",Toast.LENGTH_SHORT).show();
                    nameEditText.requestFocus();
                    return;
                }

                Call<String> call = userApi.findId(name,birthDate);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), ShowIDActivity.class);
                            intent.putExtra(Config.USER_ID, response.body());
                            startActivity(intent);
                            finish();
                        } else {
                            Snackbar.make(findViewById(android.R.id.content), "이름과 생년월일이 일치하는 ID가 없습니다.", Snackbar.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Snackbar.make(findViewById(android.R.id.content), "서버와 통신에 실패하였습니다. 다시 시도해 주세요", Snackbar.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
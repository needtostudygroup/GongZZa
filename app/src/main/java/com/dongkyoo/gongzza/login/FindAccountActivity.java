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
import com.dongkyoo.gongzza.Utils;
import com.dongkyoo.gongzza.network.Networks;
import com.dongkyoo.gongzza.network.UserApi;
import com.dongkyoo.gongzza.vos.Config;
import com.dongkyoo.gongzza.vos.User;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;

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
                try {
                    birthString = String.format("%d-%d-%d", birth.getYear(), birth.getMonth() + 1, birth.getDayOfMonth());
                    birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthString);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(FindAccountActivity.this, "이름을 입력하세요",Toast.LENGTH_SHORT).show();
                    nameEditText.requestFocus();
                    return;
                }

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String birthStr = simpleDateFormat.format(birthDate);
                Call<User> call = userApi.findId(name, birthStr);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        if (response.isSuccessful() && user != null && !TextUtils.isEmpty(user.getId())) {
                            Intent intent = new Intent(getApplicationContext(), ShowIDActivity.class);
                            intent.putExtra(Config.USER_ID, user.getId());
                            startActivity(intent);
                            finish();
                        } else {
                            Snackbar.make(findViewById(android.R.id.content), "이름과 생년월일이 일치하는 ID가 없습니다.", Snackbar.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Snackbar.make(findViewById(android.R.id.content), "서버와 통신에 실패하였습니다. 다시 시도해 주세요", Snackbar.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
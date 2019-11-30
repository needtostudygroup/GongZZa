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
import com.dongkyoo.gongzza.vos.User;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindPasswordActivity extends AppCompatActivity {

    private UserApi userApi;
    private  String birthString;
    private Date birthDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_find_password);

        Networks.createRetrofit(this);
        userApi = Networks.retrofit.create(UserApi.class);
        EditText nameEditText = (EditText)findViewById(R.id.inputName);
        EditText idEditText = (EditText)findViewById((R.id.inputId));
        DatePicker birth = (DatePicker)findViewById(R.id.inputBirth);

        findViewById(R.id.findPWButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String id = idEditText.getText().toString();

                birth.init(birth.getYear(), birth.getMonth(), birth.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        birthString = String.format("%d-%d-%d", birth.getYear(), birth.getMonth() + 1, birth.getDayOfMonth());
                        birthDate = Date.valueOf(birthString);
                    }
                });

                if(TextUtils.isEmpty(id)){
                    Toast.makeText(FindPasswordActivity.this, "ID를 입력하세요",Toast.LENGTH_SHORT).show();
                    idEditText.requestFocus();
                    return;
                }
                else if(TextUtils.isEmpty(name)){
                    Toast.makeText(FindPasswordActivity.this, "이름을 입력하세요",Toast.LENGTH_SHORT).show();
                    nameEditText.requestFocus();
                    return;
                }


                Call<Integer> call = userApi.getFindPasswordAuthority(id,name,birthDate);
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        Bundle extras = new Bundle();
                        if (response.isSuccessful()) {
                            Call<User> userCall = userApi.getUserById(id);
                            userCall.enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {
                                    extras.putString(Config.USER_ID, response.body().getId());
                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable t) {
                                    Snackbar.make(findViewById(android.R.id.content), "서버와 통신에 실패하였습니다. 다시 시도해 주세요", Snackbar.LENGTH_LONG).show();
                                }
                            });
                            Intent intent = new Intent(getApplicationContext(), PasswordChageActivity.class);
                            extras.putInt(Config.AUTHORITY,response.code());
                            intent.putExtras(extras);
                            startActivity(intent);
                            finish();
                        } else {
                            Snackbar.make(findViewById(android.R.id.content), "비밀번호 찾기 권한을 흭득하지 못했습니다.", Snackbar.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Snackbar.make(findViewById(android.R.id.content), "서버와 통신에 실패하였습니다. 다시 시도해 주세요", Snackbar.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}

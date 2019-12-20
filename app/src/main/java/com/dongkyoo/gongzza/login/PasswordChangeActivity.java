package com.dongkyoo.gongzza.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dongkyoo.gongzza.R;
import com.dongkyoo.gongzza.network.UserApi;
import com.dongkyoo.gongzza.vos.Config;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordChangeActivity extends AppCompatActivity {

    private UserApi userApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_chage);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        //흭득한 데이터 저장
        String id = extras.getString(Config.USER_ID);
        int authority = extras.getInt(Config.AUTHORITY);

        EditText passwordChangeText = findViewById(R.id.inputPassword);
        EditText passwordChangeConfirmText = findViewById(R.id.inputPasswordConfirm);

        findViewById(R.id.findPWButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String password = passwordChangeText.getText().toString();
                String pwConfirm = passwordChangeConfirmText.getText().toString();

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(PasswordChangeActivity.this, "새비밀번호를 입력하세요",Toast.LENGTH_SHORT).show();
                    passwordChangeText.requestFocus();
                    return;
                }
                else if(TextUtils.isEmpty(pwConfirm)){
                    Toast.makeText(PasswordChangeActivity.this, "비밀번호 확인을 입력하세요",Toast.LENGTH_SHORT).show();
                    passwordChangeConfirmText.requestFocus();
                    return;
                }
                if(!password.equals(pwConfirm)) {
                    Toast.makeText(PasswordChangeActivity.this, "비밀번호가 일치하지 않습니다",Toast.LENGTH_SHORT).show();
                    passwordChangeConfirmText.setText("");
                    passwordChangeConfirmText.requestFocus();
                    return;
                }

                Call<Integer> call = userApi.findPassword(id,password,authority);
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Snackbar.make(findViewById(android.R.id.content), "비밀번호 변경에 실패했습니다.", Snackbar.LENGTH_LONG).show();
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

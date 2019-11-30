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

public class PasswordChageActivity extends AppCompatActivity {

    private UserApi userApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_chage);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String id = extras.getString(Config.USER_ID);
        int authority = extras.getInt(Config.AUTHORITY);

        EditText passwordChageText = findViewById(R.id.inputPassword);
        EditText passwordChageConfirmText = findViewById(R.id.inputPasswordConfirm);

        String password = passwordChageText.getText().toString();
        String pwConfirm = passwordChageConfirmText.getText().toString();

        if(!password.equals(pwConfirm)) {
            Toast.makeText(PasswordChageActivity.this, "비밀번호가 일치하지 않습니다",Toast.LENGTH_SHORT).show();
            passwordChageConfirmText.setText("");
            passwordChageConfirmText.requestFocus();
            return;
        }

        findViewById(R.id.findPWButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String password = passwordChageText.getText().toString();
                String pwConfirm = passwordChageConfirmText.getText().toString();

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(PasswordChageActivity.this, "새비밀번호를 입력하세요",Toast.LENGTH_SHORT).show();
                    passwordChageText.requestFocus();
                    return;
                }
                else if(TextUtils.isEmpty(pwConfirm)){
                    Toast.makeText(PasswordChageActivity.this, "비밀번호 확인을 입력하세요",Toast.LENGTH_SHORT).show();
                    passwordChageConfirmText.requestFocus();
                    return;
                }
                if(!password.equals(pwConfirm)) {
                    Toast.makeText(PasswordChageActivity.this, "비밀번호가 일치하지 않습니다",Toast.LENGTH_SHORT).show();
                    passwordChageConfirmText.setText("");
                    passwordChageConfirmText.requestFocus();
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

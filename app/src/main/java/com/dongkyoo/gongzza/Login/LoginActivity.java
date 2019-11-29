package com.dongkyoo.gongzza.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dongkyoo.gongzza.MockData;
import com.dongkyoo.gongzza.R;
import com.dongkyoo.gongzza.home.HomeFragment;
import com.dongkyoo.gongzza.main.MainActivity;
import com.dongkyoo.gongzza.network.Networks;
import com.dongkyoo.gongzza.network.UserApi;
import com.dongkyoo.gongzza.vos.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class LoginActivity extends AppCompatActivity {
    private String TAG;

    UserApi userApi = Networks.retrofit.create(UserApi.class);
    Call<User> logIn = userApi.getUserByIdPw(MockData.getMockUser().getId(),MockData.getMockUser().getPassword());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        User user = new User();

        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener(){
            EditText loginEtId = (EditText)findViewById(R.id.inputId);
            EditText loginEtPassword = (EditText)findViewById((R.id.inputPassword));

            @Override
            public void onClick(View view) {
                if(loginEtId.getText().toString().length() == 0){
                    Toast.makeText(LoginActivity.this, "ID를 입력하세요",Toast.LENGTH_SHORT).show();
                    loginEtId.requestFocus();
                    return;
                }
                else if(loginEtPassword.getText().toString().length() == 0){
                    Toast.makeText(LoginActivity.this, "비밀번호를 입력하세요",Toast.LENGTH_SHORT).show();
                    loginEtPassword.requestFocus();
                    return;
                }
                else {
                    //@GET("/users")
                    //Call<User> getUserByIdPw(@Query("id") String id, @Query("password") String password);
                    logIn.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.code() == 200) {
                                Toast.makeText(LoginActivity.this, "로그인 되었습니다.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "통신은 성공했으나 로그인에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "서버와 통신에 실패하였습니다. 다시 시도해 주세요.",Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "로그인 실패", t);
                        }
                    });
                }
            }
        });

        Button signupBtn = (Button)findViewById(R.id.signupButton);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });

        Button findAccountBtn = (Button)findViewById(R.id.findAccount);
        findAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FindAccountActivity.class);
                startActivity(intent);
            }
        });

        Button findPasswordBtn = (Button)findViewById(R.id.findPassword);
        findPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FindPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}

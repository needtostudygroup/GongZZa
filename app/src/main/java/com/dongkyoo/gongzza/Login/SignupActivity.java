package com.dongkyoo.gongzza.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dongkyoo.gongzza.MockData;
import com.dongkyoo.gongzza.R;
import com.dongkyoo.gongzza.network.Networks;
import com.dongkyoo.gongzza.network.UserApi;
import com.dongkyoo.gongzza.vos.AuthMail;
import com.dongkyoo.gongzza.vos.User;

import org.w3c.dom.Text;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Tag;

public class SignupActivity extends AppCompatActivity {

    UserApi userApi = Networks.retrofit.create(UserApi.class);
    Call<User> signUp = userApi.signUp(MockData.getMockUser());
    Call<AuthMail> sendAuthenticateEmail = userApi.sendAuthenticateEmail(MockData.getMockUser().getId(), MockData.getMockUser().getEmail());

    DatePicker Birth;
    private  String birthString;
    private Date birthDate;
    private  boolean isChanged = false;
    private  boolean isComplete = false;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_signup);

        //객체성성
        User user = new User();
        AuthMail authMail = new AuthMail();

        Birth = (DatePicker)findViewById(R.id.inputBirth);
        //현재날짜로 초기화
        Birth.init(Birth.getYear(), Birth.getMonth(), Birth.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                birthString = String.format("%d-%d-%d", Birth.getYear(), Birth.getMonth() + 1, Birth.getDayOfMonth());
                birthDate = Date.valueOf(birthString);
                isChanged = true;
            }
        });

        findViewById(R.id.emailConfirmButton).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                builder.setTitle("인증 이메일이 발송되었습니다!");
                builder.setMessage("등록된 이메일로 접속하여 메일을 확인하셔야 가입이 완료됩니다.");
                builder.setPositiveButton("확인",null);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                //서버와 통신
                sendAuthenticateEmail.enqueue(new Callback<AuthMail>() {

                    @Override
                    public void onResponse(Call<AuthMail> call, Response<AuthMail> response) {
                        //@POST("/authMails")
                        //Call<AuthMail> sendAuthenticateEmail(@Query("userId") String userId, @Query("email") String email);
                        Call<AuthMail> call1 = userApi.sendAuthenticateEmail("abcdef1","f7817455@naver.com");
                        if (response.code() == 200) {
                            Toast.makeText(SignupActivity.this, "이메일 인증이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(SignupActivity.this, "통신은 성공했으나 인증에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<AuthMail> call, Throwable t) {
                        Toast.makeText(SignupActivity.this, "서버와 통신에 실패하였습니다. 다시 시도해 주세요.",Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "이메일 전송 실패", t);
                    }
                });
            }
        });

        findViewById(R.id.signupedButton).setOnClickListener(new View.OnClickListener(){
            EditText signupEtName = (EditText)findViewById(R.id.inputName);
            EditText signupEtEmail = (EditText)findViewById((R.id.inputEmailCheck));
            EditText signupEtPassword = (EditText)findViewById((R.id.inputPassword));
            EditText signupEtPasswordConfirm = (EditText)findViewById((R.id.inputPasswordConfirm));
            EditText signupEtId = (EditText)findViewById(R.id.inputId);

            @Override
            public void onClick(View view) {
                if(signupEtName.getText().toString().length() == 0){
                    Toast.makeText(SignupActivity.this, "이름을 입력하세요",Toast.LENGTH_SHORT).show();
                    signupEtName.requestFocus();
                    return;
                }
                else
                    user.setName(signupEtName.getText().toString());

                if(signupEtEmail.getText().toString().length() == 0){
                    Toast.makeText(SignupActivity.this, "Email을 입력하세요",Toast.LENGTH_SHORT).show();
                    signupEtEmail.requestFocus();
                    return;
                }
                else
                    user.setEmail(signupEtEmail.getText().toString());

                if(signupEtId.getText().toString().length() == 0) {
                    Toast.makeText(SignupActivity.this, "ID를 입력하세요", Toast.LENGTH_SHORT).show();
                    signupEtId.requestFocus();
                    return;
                }
                else
                    user.setId(signupEtId.getText().toString());

                if(signupEtPassword.getText().toString().length() == 0){
                    Toast.makeText(SignupActivity.this, "Password를 입력하세요",Toast.LENGTH_SHORT).show();
                    signupEtPassword.requestFocus();
                    return;
                }
                else if(signupEtPasswordConfirm.getText().toString().length() == 0){
                    Toast.makeText(SignupActivity.this, "Password Confirm을 입력하세요",Toast.LENGTH_SHORT).show();
                    signupEtPasswordConfirm.requestFocus();
                    return;
                }
                else if(!signupEtPassword.getText().toString().equals(signupEtPasswordConfirm.getText().toString())){
                    Toast.makeText(SignupActivity.this, "비밀번호가 일치하지 않습니다",Toast.LENGTH_SHORT).show();
                    signupEtPasswordConfirm.setText("");
                    signupEtPasswordConfirm.requestFocus();
                    return;
                }
                else
                    user.setPassword(signupEtPasswordConfirm.getText().toString());

                if (isChanged){
                    Toast.makeText(SignupActivity.this,birthString,Toast.LENGTH_SHORT).show();
                    user.setBirthday(birthDate);
                    isComplete = true;
                }
                else
                    Toast.makeText(SignupActivity.this, "생년월일을 입력해 주세요.",Toast.LENGTH_SHORT).show();

                if(isComplete){
                    //모든 데이터 입력 했을 시
                    //서버에 데이터 전송
                    signUp.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            //@POST("/users")
                            //Call<User> signUp(@Body User user);
                            if (response.code() == 200) {
                                Toast.makeText(SignupActivity.this, "회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(SignupActivity.this, "통신은 성공했으나 회원가입에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(SignupActivity.this, "서버와 통신에 실패하였습니다. 다시 시도해 주세요.",Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "가입 실패", t);
                        }
                    });

                }
            }
        });
    }
}

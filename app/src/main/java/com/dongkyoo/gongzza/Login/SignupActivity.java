package com.dongkyoo.gongzza.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dongkyoo.gongzza.R;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    DatePicker Birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_signup);

        Birth = (DatePicker)findViewById(R.id.inputBirth);
        //현재날짜로 초기화
        Birth.init(Birth.getYear(), Birth.getMonth(), Birth.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String res = String.format("%d년 %d월 %d일", Birth.getYear() +1, Birth.getMonth(), Birth.getDayOfMonth());
                Toast.makeText(SignupActivity.this,res,Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.signupedButton).setOnClickListener(new View.OnClickListener(){
            EditText signupEtName = (EditText)findViewById(R.id.inputName);
            EditText signupEtEmail = (EditText)findViewById((R.id.inputEmail));
            EditText signupEtPassword = (EditText)findViewById((R.id.inputPassword));
            EditText signupEtPasswordConfirm = (EditText)findViewById((R.id.inputPasswordConfirm));

            @Override
            public void onClick(View v) {
                if(signupEtName.getText().toString().length() == 0){
                    Toast.makeText(SignupActivity.this, "이름을 입력하세요",Toast.LENGTH_SHORT).show();
                    signupEtName.requestFocus();
                    return;
                }
                else if(signupEtEmail.getText().toString().length() == 0){
                    Toast.makeText(SignupActivity.this, "Email을 입력하세요",Toast.LENGTH_SHORT).show();
                    signupEtEmail.requestFocus();
                    return;
                }
                else if(signupEtPassword.getText().toString().length() == 0){
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
                else{
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });


    }
}

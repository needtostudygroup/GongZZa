package com.dongkyoo.gongzza.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dongkyoo.gongzza.MockData;
import com.dongkyoo.gongzza.R;
import com.dongkyoo.gongzza.network.Networks;
import com.dongkyoo.gongzza.network.UserApi;
import com.dongkyoo.gongzza.vos.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class FindAccountActivity extends AppCompatActivity {
    private String TAG;
    UserApi userApi = Networks.retrofit.create(UserApi.class);
    Call<String> findAccount = userApi.findId(MockData.getMockUser().getName(),MockData.getMockUser().getBirthday());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_find_account);

        findViewById(R.id.signupedButton).setOnClickListener(new View.OnClickListener(){
            EditText findAccountEtName = (EditText)findViewById(R.id.inputName);

            @Override
            public void onClick(View v) {
                if(findAccountEtName.getText().toString().length() == 0){
                    Toast.makeText(FindAccountActivity.this, "이름을 입력하세요",Toast.LENGTH_SHORT).show();
                    findAccountEtName.requestFocus();
                    return;
                }
                else{
                    //@GET("/users/find/id")
                    //Call<String> findId(@Query("name") String name, @Query("birthday") Date birthday);
                    findAccount.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.code() == 200) {
                                Toast.makeText(FindAccountActivity.this, "아이디를 찾았습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(FindAccountActivity.this, "통신은 성공했으나 아이디찾기에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(FindAccountActivity.this, "서버와 통신에 실패하였습니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "아이디찾기 실패", t);
                        }
                    });
                }
            }
        });
    }
}
